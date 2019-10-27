package com.javiersc.resource.network.adapter.suspend

import com.javiersc.resource.extensions.printlnError
import com.javiersc.resource.extensions.printlnWarning
import com.javiersc.resource.network.NetworkResponse
import com.javiersc.resource.network.adapter.suspend.handlers.httpExceptionSuspendHandler
import com.javiersc.resource.network.adapter.suspend.handlers.responseSuspendHandler
import com.javiersc.resource.network.adapter.utils.isInternetAvailable
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.*
import java.io.EOFException
import java.net.ConnectException
import java.net.UnknownHostException

internal class NetworkResponseSuspendCall<R : Any, E : Any>(
    private val backingCall: Call<R>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<R, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<R, E>>) = synchronized(this) {
        backingCall.enqueue(object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                response.responseSuspendHandler(
                    errorConverter,
                    this@NetworkResponseSuspendCall,
                    callback
                )
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                when (throwable) {
                    is UnknownHostException -> onUnknownHostException(callback, throwable)
                    is EOFException -> onEOFException(callback)
                    is IllegalStateException -> onIllegalStateException(throwable)
                    is ConnectException -> onConnectionException(callback, throwable)
                    is HttpException -> onHttpException(callback, throwable)
                    else -> throw Throwable("${throwable.message}")
                }
            }
        })
    }

    override fun isExecuted(): Boolean = synchronized(this) { backingCall.isExecuted }

    override fun clone(): Call<NetworkResponse<R, E>> =
        NetworkResponseSuspendCall(backingCall.clone(), errorConverter)

    override fun isCanceled(): Boolean = synchronized(this) { backingCall.isCanceled }

    override fun cancel() = synchronized(this) { backingCall.cancel() }

    override fun execute(): Response<NetworkResponse<R, E>> =
        throw UnsupportedOperationException("Suspend call does not support synchronous execution")

    override fun request(): Request = backingCall.request()

    private fun onUnknownHostException(
        callback: Callback<NetworkResponse<R, E>>,
        throwable: Throwable
    ) {
        val message = "${throwable.message}"
        if (isInternetAvailable) callback.onResponse(
            this,
            Response.success(NetworkResponse.RemoteError(message))
        ) else callback.onResponse(
            this,
            Response.success(NetworkResponse.InternetNotAvailable(message))
        )
    }

    private fun onEOFException(callback: Callback<NetworkResponse<R, E>>) {
        printlnWarning(
            """
               | # # # # # # # # # # # # # # WARNING # # # # # # # # # # # # # # # # # # #
               | # Every 2XX response should have a body except 204/205, as the response #
               | # was empty, the NetworkResponse is transformed to NoContent (204)      #
               | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
               """.trimMargin()
        )
        callback.onResponse(
            this,
            Response.success(NetworkResponse.Success.NoContent(headers = null))
        )
    }


    private fun onIllegalStateException(throwable: Throwable) {
        printlnError(
            """
               | # # # # # # # # # # # # # # ERROR # # # # # # # # # # # # # # #
               | # Response body can't be serialized with the object provided" #
               | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
               """.trimMargin()
        )
        throw IllegalStateException(throwable.localizedMessage)
    }

    private fun onConnectionException(
        callback: Callback<NetworkResponse<R, E>>,
        throwable: Throwable
    ) {
        callback.onResponse(
            this,
            Response.success(NetworkResponse.RemoteError(throwable.localizedMessage))
        )
    }

    private fun onHttpException(
        callback: Callback<NetworkResponse<R, E>>,
        exception: HttpException
    ) {
        exception.httpExceptionSuspendHandler(
            errorConverter,
            this,
            callback
        )
    }
} 