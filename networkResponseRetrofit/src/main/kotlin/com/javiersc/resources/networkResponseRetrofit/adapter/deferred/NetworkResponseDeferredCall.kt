package com.javiersc.resources.networkResponseRetrofit.adapter.deferred

import com.javiersc.resources.networkResponseRetrofit.NetworkResponse
import com.javiersc.resources.networkResponseRetrofit.adapter.deferred.handlers.httpExceptionDeferredHandler
import com.javiersc.resources.networkResponseRetrofit.adapter.deferred.handlers.responseDeferredHandler
import com.javiersc.resources.networkResponseRetrofit.adapter.utils.isInternetAvailable
import com.javiersc.resources.networkResponseRetrofit.utils.printlnError
import com.javiersc.resources.networkResponseRetrofit.utils.printlnWarning
import kotlinx.coroutines.CompletableDeferred
import okhttp3.ResponseBody
import retrofit2.*
import java.io.EOFException
import java.net.ConnectException
import java.net.UnknownHostException

internal fun <R : Any, E : Any> deferredAdapt(
    call: Call<R>,
    errorConverter: Converter<ResponseBody, E>
): CompletableDeferred<NetworkResponse<R, E>> {
    val deferred = CompletableDeferred<NetworkResponse<R, E>>()

    deferred.invokeOnCompletion { if (deferred.isCancelled) call.cancel() }

    call.enqueue(object : Callback<R> {
        override fun onFailure(call: Call<R>, throwable: Throwable) {
            when (throwable) {
                is UnknownHostException -> onUnknownException(
                    deferred,
                    throwable
                )
                is EOFException -> onEOFException(
                    deferred
                )
                is IllegalStateException -> onIllegalStateException(
                    throwable
                )
                is ConnectException -> onConnectException(
                    deferred,
                    throwable
                )
                is HttpException -> onHttpException(
                    deferred,
                    errorConverter,
                    throwable
                )
                else -> throw Throwable("${throwable.message}")
            }
        }

        override fun onResponse(call: Call<R>, response: Response<R>) {
            response.responseDeferredHandler(errorConverter, deferred)
        }
    })
    return deferred
}

private fun <R, E> onUnknownException(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    throwable: Throwable
) {
    val message = "${throwable.message}"
    if (isInternetAvailable) deferred.complete(
        NetworkResponse.RemoteError(message))
    else deferred.complete(NetworkResponse.InternetNotAvailable(message))
}

private fun <R, E> onEOFException(deferred: CompletableDeferred<NetworkResponse<R, E>>) {
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

private fun onIllegalStateException(
    throwable: Throwable
) {
    printlnError("Response body can't be serialized with the object provided.")
    throw IllegalStateException(throwable.localizedMessage)
}

private fun <R, E> onConnectException(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    throwable: Throwable
) {
    deferred.complete(NetworkResponse.RemoteError(throwable.localizedMessage))
}

private fun <R : Any, E : Any> onHttpException(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    errorConverter: Converter<ResponseBody, E>,
    exception: HttpException
) {
    exception.httpExceptionDeferredHandler(errorConverter, deferred)
}
