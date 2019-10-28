package com.javiersc.resource.network.adapter.deferred.handlers

import com.javiersc.resource.extensions.printlnError
import com.javiersc.resource.network.NetworkResponse
import com.javiersc.resource.network.NetworkResponse.ClientError.*
import com.javiersc.resource.network.NetworkResponse.Info.*
import com.javiersc.resource.network.NetworkResponse.NonGenericStatus
import com.javiersc.resource.network.NetworkResponse.Redirection.*
import com.javiersc.resource.network.NetworkResponse.ServerError.*
import com.javiersc.resource.network.NetworkResponse.Success.*
import kotlinx.coroutines.CompletableDeferred
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response

internal fun <R : Any, E : Any> Response<R>.responseDeferredHandler(
    errorConverter: Converter<ResponseBody, E>,
    deferred: CompletableDeferred<NetworkResponse<R, E>>
) {

    val body: R? = body()
    val code: Int = code()
    val headers: Headers = headers()
    val errorBody: E? = when {
        errorBody() == null -> null
        errorBody()?.contentLength() ?: 0L == 0L -> null
        else -> try {
            errorBody()?.let { errorConverter.convert(it) }
        } catch (e: Exception) {
            printlnError("Error body can't be serialized with the error object provided")
            null
        }
    }

    with(deferred) {
        when {
            body == null && code in 200..299 -> complete(NoContent(headers))
            body != null && code in 200..299 -> when (code) {
                200 -> complete(OK(body, headers))
                201 -> complete(Created(body, headers))
                202 -> complete(Accepted(body, headers))
                203 -> complete(NonAuthoritativeInformation(body, headers))
                204 -> complete(NoContent(headers))
                205 -> complete(ResetContent(headers))
                206 -> complete(PartialContent(body, headers))
                207 -> complete(MultiStatus(body, headers))
                208 -> complete(AlreadyReported(body, headers))
                226 -> complete(ImUsed(body, headers))
                else -> complete(OK(body, headers))
            }
            else -> when (code) {
                100 -> complete(Continue(headers))
                101 -> complete(SwitchingProtocol(headers))
                102 -> complete(Processing(headers))

                300 -> complete(MultipleChoices(headers))
                301 -> complete(MovedPermanently(headers))
                302 -> complete(Found(headers))
                303 -> complete(SeeOther(headers))
                304 -> complete(NotModified(headers))
                305 -> complete(UseProxy(headers))
                306 -> complete(SwitchProxy(headers))
                307 -> complete(TemporaryRedirect(headers))
                308 -> complete(PermanentRedirect(headers))

                400 -> complete(BadRequest(errorBody, headers))
                401 -> complete(Unauthorized(errorBody, headers))
                402 -> complete(PaymentRequired(errorBody, headers))
                403 -> complete(Forbidden(errorBody, headers))
                404 -> complete(NotFound(errorBody, headers))
                405 -> complete(MethodNotAllowed(errorBody, headers))
                406 -> complete(NotAcceptable(errorBody, headers))
                407 -> complete(ProxyAuthenticationRequired(errorBody, headers))
                408 -> complete(RequestTimeout(errorBody, headers))
                409 -> complete(Conflict(errorBody, headers))
                410 -> complete(Gone(errorBody, headers))
                411 -> complete(LengthRequired(errorBody, headers))
                412 -> complete(PreconditionFailed(errorBody, headers))
                413 -> complete(PayloadTooLarge(errorBody, headers))
                414 -> complete(URITooLong(errorBody, headers))
                415 -> complete(UnsupportedMediaType(errorBody, headers))
                416 -> complete(RequestedRangeNotSatisfiable(errorBody, headers))
                417 -> complete(ExpectationFailed(errorBody, headers))
                418 -> complete(ImATeapot(errorBody, headers))
                421 -> complete(MisdirectedRequest(errorBody, headers))
                422 -> complete(UnprocessableEntity(errorBody, headers))
                423 -> complete(Locked(errorBody, headers))
                424 -> complete(FailedDependency(errorBody, headers))
                426 -> complete(UpgradeRequired(errorBody, headers))
                428 -> complete(PreconditionRequired(errorBody, headers))
                429 -> complete(TooManyRequest(errorBody, headers))
                431 -> complete(RequestHeaderFieldsTooLarge(errorBody, headers))
                451 -> complete(UnavailableForLegalReasons(errorBody, headers))

                500 -> complete(InternalServerError(errorBody, headers))
                501 -> complete(NotImplemented(errorBody, headers))
                502 -> complete(BadGateway(errorBody, headers))
                503 -> complete(ServiceUnavailable(errorBody, headers))
                504 -> complete(GatewayTimeout(errorBody, headers))
                505 -> complete(HTTPVersionNotSupported(errorBody, headers))
                506 -> complete(VariantAlsoNegotiates(errorBody, headers))
                507 -> complete(InsufficientStorage(errorBody, headers))
                508 -> complete(LoopDetected(errorBody, headers))
                510 -> complete(NotExtended(errorBody, headers))
                511 -> complete(NetworkAuthenticationRequired(errorBody, headers))

                else -> complete(NonGenericStatus(body, errorBody, code, headers))
            }
        }
    }
}