package com.javiersc.resource.network.adapter.suspend.handlers

import com.javiersc.resource.network.NetworkResponse
import com.javiersc.resource.network.NetworkResponse.ClientError.*
import com.javiersc.resource.network.NetworkResponse.Info.*
import com.javiersc.resource.network.NetworkResponse.NonGenericError
import com.javiersc.resource.network.NetworkResponse.Redirection.*
import com.javiersc.resource.network.NetworkResponse.ServerError.*
import com.javiersc.resource.network.adapter.suspend.NetworkResponseSuspendCall
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response


internal fun <R : Any, E : Any> HttpException.httpExceptionSuspendHandler(
    errorConverter: Converter<ResponseBody, E>,
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>
) {
    val errorBody = this.response()?.errorBody()?.let { errorConverter.convert(it) }
    val code = this.code()
    val headers = this.response()?.headers()

    with(callback) {
        when (code) {
            100 -> onResponse(call, Response.success(Continue(headers)))
            101 -> onResponse(call, Response.success(SwitchingProtocol(headers)))
            102 -> onResponse(call, Response.success(Processing(headers)))

            300 -> onResponse(call, Response.success(MultipleChoices(headers)))
            301 -> onResponse(call, Response.success(MovedPermanently(headers)))
            302 -> onResponse(call, Response.success(Found(headers)))
            303 -> onResponse(call, Response.success(SeeOther(headers)))
            304 -> onResponse(call, Response.success(NotModified(headers)))
            305 -> onResponse(call, Response.success(UseProxy(headers)))
            306 -> onResponse(call, Response.success(SwitchProxy(headers)))
            307 -> onResponse(call, Response.success(TemporaryRedirect(headers)))
            308 -> onResponse(call, Response.success(PermanentRedirect(headers)))

            400 -> onResponse(call, Response.success(BadRequest(errorBody, headers)))
            401 -> onResponse(call, Response.success(Unauthorized(errorBody, headers)))
            402 -> onResponse(call, Response.success(PaymentRequired(errorBody, headers)))
            403 -> onResponse(call, Response.success(Forbidden(errorBody, headers)))
            404 -> onResponse(call, Response.success(NotFound(errorBody, headers)))
            405 -> onResponse(call, Response.success(MethodNotAllowed(errorBody, headers)))
            406 -> onResponse(call, Response.success(NotAcceptable(errorBody, headers)))
            407 ->
                onResponse(call, Response.success(ProxyAuthenticationRequired(errorBody, headers)))
            408 -> onResponse(call, Response.success(RequestTimeout(errorBody, headers)))
            409 -> onResponse(call, Response.success(Conflict(errorBody, headers)))
            410 -> onResponse(call, Response.success(Gone(errorBody, headers)))
            411 -> onResponse(call, Response.success(LengthRequired(errorBody, headers)))
            412 -> onResponse(call, Response.success(PreconditionFailed(errorBody, headers)))
            413 -> onResponse(call, Response.success(PayloadTooLarge(errorBody, headers)))
            414 -> onResponse(call, Response.success(URITooLong(errorBody, headers)))
            415 -> onResponse(call, Response.success(UnsupportedMediaType(errorBody, headers)))
            416 ->
                onResponse(call, Response.success(RequestedRangeNotSatisfiable(errorBody, headers)))
            417 -> onResponse(call, Response.success(ExpectationFailed(errorBody, headers)))
            418 -> onResponse(call, Response.success(ImATeapot(errorBody, headers)))
            421 -> onResponse(call, Response.success(MisdirectedRequest(errorBody, headers)))
            422 -> onResponse(call, Response.success(UnprocessableEntity(errorBody, headers)))
            423 -> onResponse(call, Response.success(Locked(errorBody, headers)))
            424 -> onResponse(call, Response.success(FailedDependency(errorBody, headers)))
            426 -> onResponse(call, Response.success(UpgradeRequired(errorBody, headers)))
            428 -> onResponse(call, Response.success(PreconditionRequired(errorBody, headers)))
            429 -> onResponse(call, Response.success(TooManyRequest(errorBody, headers)))
            431 ->
                onResponse(call, Response.success(RequestHeaderFieldsTooLarge(errorBody, headers)))
            451 ->
                onResponse(call, Response.success(UnavailableForLegalReasons(errorBody, headers)))

            500 -> onResponse(call, Response.success(InternalServerError(errorBody, headers)))
            501 -> onResponse(call, Response.success(NotImplemented(errorBody, headers)))
            502 -> onResponse(call, Response.success(BadGateway(errorBody, headers)))
            503 -> onResponse(call, Response.success(ServiceUnavailable(errorBody, headers)))
            504 -> onResponse(call, Response.success(GatewayTimeout(errorBody, headers)))
            505 -> onResponse(call, Response.success(HTTPVersionNotSupported(errorBody, headers)))
            506 -> onResponse(call, Response.success(VariantAlsoNegotiates(errorBody, headers)))
            507 -> onResponse(call, Response.success(InsufficientStorage(errorBody, headers)))
            508 -> onResponse(call, Response.success(LoopDetected(errorBody, headers)))
            510 -> onResponse(call, Response.success(NotExtended(errorBody, headers)))
            511 ->
                onResponse(
                    call,
                    Response.success(NetworkAuthenticationRequired(errorBody, headers))
                )

            else -> onResponse(call, Response.success(NonGenericError(errorBody, code, headers)))
        }
    }
}
