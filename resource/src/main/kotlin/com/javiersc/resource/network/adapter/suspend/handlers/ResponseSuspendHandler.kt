package com.javiersc.resource.network.adapter.suspend.handlers


import com.javiersc.resource.extensions.printlnError
import com.javiersc.resource.network.NetworkResponse
import com.javiersc.resource.network.NetworkResponse.ClientError.*
import com.javiersc.resource.network.NetworkResponse.Info.*
import com.javiersc.resource.network.NetworkResponse.NonGenericStatus
import com.javiersc.resource.network.NetworkResponse.Redirection.*
import com.javiersc.resource.network.NetworkResponse.ServerError.*
import com.javiersc.resource.network.NetworkResponse.Success.*
import com.javiersc.resource.network.adapter.suspend.NetworkResponseSuspendCall
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Response.success

internal fun <R : Any, E : Any> Response<R>.responseSuspendHandler(
    errorConverter: Converter<ResponseBody, E>,
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>
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

    with(callback) {
        when {
            body == null && code in 200..299 -> onResponse(call, success(NoContent(headers)))
            body != null && code in 200..299 -> when (code) {
                200 -> onResponse(call, success(OK(body, headers)))
                201 -> onResponse(call, success(Created(body, headers)))
                202 -> onResponse(call, success(Accepted(body, headers)))
                203 -> onResponse(call, success(NonAuthoritativeInformation(body, headers)))
                204 -> onResponse(call, success(NoContent(headers)))
                205 -> onResponse(call, success(ResetContent(headers)))
                206 -> onResponse(call, success(PartialContent(body, headers)))
                207 -> onResponse(call, success(MultiStatus(body, headers)))
                208 -> onResponse(call, success(AlreadyReported(body, headers)))
                226 -> onResponse(call, success(ImUsed(body, headers)))
            }
            else -> when (code) {
                100 -> onResponse(call, success(Continue(headers)))
                101 -> onResponse(call, success(SwitchingProtocol(headers)))
                102 -> onResponse(call, success(Processing(headers)))

                300 -> onResponse(call, success(MultipleChoices(headers)))
                301 -> onResponse(call, success(MovedPermanently(headers)))
                302 -> onResponse(call, success(Found(headers)))
                303 -> onResponse(call, success(SeeOther(headers)))
                304 -> onResponse(call, success(NotModified(headers)))
                305 -> onResponse(call, success(UseProxy(headers)))
                306 -> onResponse(call, success(SwitchProxy(headers)))
                307 -> onResponse(call, success(TemporaryRedirect(headers)))
                308 -> onResponse(call, success(PermanentRedirect(headers)))

                400 -> onResponse(call, success(BadRequest(errorBody, headers)))
                401 -> onResponse(call, success(Unauthorized(errorBody, headers)))
                402 -> onResponse(call, success(PaymentRequired(errorBody, headers)))
                403 -> onResponse(call, success(Forbidden(errorBody, headers)))
                404 -> onResponse(call, success(NotFound(errorBody, headers)))
                405 -> onResponse(call, success(MethodNotAllowed(errorBody, headers)))
                406 -> onResponse(call, success(NotAcceptable(errorBody, headers)))
                407 -> onResponse(call, success(ProxyAuthenticationRequired(errorBody, headers)))
                408 -> onResponse(call, success(RequestTimeout(errorBody, headers)))
                409 -> onResponse(call, success(Conflict(errorBody, headers)))
                410 -> onResponse(call, success(Gone(errorBody, headers)))
                411 -> onResponse(call, success(LengthRequired(errorBody, headers)))
                412 -> onResponse(call, success(PreconditionFailed(errorBody, headers)))
                413 -> onResponse(call, success(PayloadTooLarge(errorBody, headers)))
                414 -> onResponse(call, success(URITooLong(errorBody, headers)))
                415 -> onResponse(call, success(UnsupportedMediaType(errorBody, headers)))
                416 -> onResponse(call, success(RequestedRangeNotSatisfiable(errorBody, headers)))
                417 -> onResponse(call, success(ExpectationFailed(errorBody, headers)))
                418 -> onResponse(call, success(ImATeapot(errorBody, headers)))
                421 -> onResponse(call, success(MisdirectedRequest(errorBody, headers)))
                422 -> onResponse(call, success(UnprocessableEntity(errorBody, headers)))
                423 -> onResponse(call, success(Locked(errorBody, headers)))
                424 -> onResponse(call, success(FailedDependency(errorBody, headers)))
                426 -> onResponse(call, success(UpgradeRequired(errorBody, headers)))
                428 -> onResponse(call, success(PreconditionRequired(errorBody, headers)))
                429 -> onResponse(call, success(TooManyRequest(errorBody, headers)))
                431 -> onResponse(call, success(RequestHeaderFieldsTooLarge(errorBody, headers)))
                451 -> onResponse(call, success(UnavailableForLegalReasons(errorBody, headers)))

                500 -> onResponse(call, success(InternalServerError(errorBody, headers)))
                501 -> onResponse(call, success(NotImplemented(errorBody, headers)))
                502 -> onResponse(call, success(BadGateway(errorBody, headers)))
                503 -> onResponse(call, success(ServiceUnavailable(errorBody, headers)))
                504 -> onResponse(call, success(GatewayTimeout(errorBody, headers)))
                505 -> onResponse(call, success(HTTPVersionNotSupported(errorBody, headers)))
                506 -> onResponse(call, success(VariantAlsoNegotiates(errorBody, headers)))
                507 -> onResponse(call, success(InsufficientStorage(errorBody, headers)))
                508 -> onResponse(call, success(LoopDetected(errorBody, headers)))
                510 -> onResponse(call, success(NotExtended(errorBody, headers)))
                511 -> onResponse(call, success(NetworkAuthenticationRequired(errorBody, headers)))

                else -> onResponse(call, success(NonGenericStatus(body, errorBody, code, headers)))
            }
        }
    }
}