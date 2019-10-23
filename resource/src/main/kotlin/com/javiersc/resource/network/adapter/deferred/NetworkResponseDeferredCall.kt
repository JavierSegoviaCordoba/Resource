package com.javiersc.resource.network.adapter.deferred

import com.javiersc.resource.extensions.printlnError
import com.javiersc.resource.extensions.printlnWarning
import com.javiersc.resource.network.NetworkResponse
import com.javiersc.resource.network.adapter.deferred.handlers.httpExceptionDeferredHandler
import com.javiersc.resource.network.adapter.deferred.handlers.responseDeferredHandler
import kotlinx.coroutines.CompletableDeferred
import okhttp3.ResponseBody
import retrofit2.*
import java.io.EOFException
import java.io.IOException
import java.net.ConnectException

internal fun <R : Any, E : Any> deferredAdapt(
    call: Call<R>,
    errorConverter: Converter<ResponseBody, E>
): CompletableDeferred<NetworkResponse<R, E>> {
    val deferred = CompletableDeferred<NetworkResponse<R, E>>()

    deferred.invokeOnCompletion { if (deferred.isCancelled) call.cancel() }

    call.enqueue(object : Callback<R> {
        override fun onFailure(call: Call<R>, throwable: Throwable) {
            when (throwable) {
                is ConnectException ->
                    deferred.complete(NetworkResponse.RemoteError(throwable.localizedMessage))
                is EOFException -> {
                    printlnWarning(
                        """
                        | # # # # # # # # # # # # # # WARNING # # # # # # # # # # # # # # # # # # #
                        | # Every 2XX response should have a body except 204/205, as the response #
                        | # was empty, the NetworkResponse is transformed to NoContent (204)      #
                        | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
                        """.trimMargin()
                    )
                    deferred.complete(NetworkResponse.Success.NoContent(headers = null))
                }
                is IOException -> deferred.complete(NetworkResponse.InternetNotAvailable(throwable))
                is HttpException ->
                    throwable.httpExceptionDeferredHandler(errorConverter, deferred)
                is IllegalStateException -> {
                    printlnError("Response body can't be serialized with the object provided.")
                    throw IllegalStateException(throwable.localizedMessage)
                }
                else -> throw Throwable(throwable.localizedMessage)
            }
        }

        override fun onResponse(call: Call<R>, response: Response<R>) {
            response.responseDeferredHandler(errorConverter, deferred)
        }
    })
    return deferred
}
