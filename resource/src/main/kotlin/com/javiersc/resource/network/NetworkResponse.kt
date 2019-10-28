package com.javiersc.resource.network

import okhttp3.Headers

sealed class NetworkResponse<out NR, out E> {

    sealed class Info : NetworkResponse<Nothing, Nothing>() {
        data class Any(val code: Int, val headers: Headers? = null) : Info()
        data class Continue(val headers: Headers? = null) : Info()
        data class SwitchingProtocol(val headers: Headers? = null) : Info()
        data class Processing(val headers: Headers? = null) : Info()
    }

    sealed class Success<out NR> : NetworkResponse<NR, Nothing>() {
        data class Any<out NR>(val value: NR? = null, val code: Int, val headers: Headers? = null) :
            Success<NR>()

        data class OK<out NR>(val value: NR, val headers: Headers? = null) : Success<NR>()
        data class Created<out NR>(val value: NR, val headers: Headers? = null) : Success<NR>()
        data class Accepted<out NR>(val value: NR, val headers: Headers? = null) : Success<NR>()
        data class NonAuthoritativeInformation<out NR>(val value: NR, val headers: Headers? = null) :
            Success<NR>()

        data class NoContent<out NR>(val headers: Headers? = null) : Success<NR>()
        data class ResetContent<out NR>(val headers: Headers? = null) : Success<NR>()
        data class PartialContent<out NR>(val value: NR, val headers: Headers? = null) : Success<NR>()
        data class MultiStatus<out NR>(val value: NR, val headers: Headers? = null) : Success<NR>()
        data class AlreadyReported<out NR>(val value: NR, val headers: Headers? = null) : Success<NR>()
        data class ImUsed<out NR>(val value: NR, val headers: Headers? = null) : Success<NR>()
    }

    sealed class Redirection : NetworkResponse<Nothing, Nothing>() {
        data class Any(val code: Int, val headers: Headers? = null) : Redirection()
        data class MultipleChoices(val headers: Headers? = null) : Redirection()
        data class MovedPermanently(val headers: Headers? = null) : Redirection()
        data class Found(val headers: Headers? = null) : Redirection()
        data class SeeOther(val headers: Headers? = null) : Redirection()
        data class NotModified(val headers: Headers? = null) : Redirection()
        data class UseProxy(val headers: Headers? = null) : Redirection()
        data class SwitchProxy(val headers: Headers? = null) : Redirection()
        data class TemporaryRedirect(val headers: Headers? = null) : Redirection()
        data class PermanentRedirect(val headers: Headers? = null) : Redirection()
    }

    sealed class ClientError<out E> : NetworkResponse<Nothing, E>() {
        data class Any<E>(val error: E? = null, val code: Int, val headers: Headers? = null) :
            ClientError<E>()

        data class BadRequest<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class Unauthorized<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class PaymentRequired<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class Forbidden<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class NotFound<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class MethodNotAllowed<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class NotAcceptable<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class ProxyAuthenticationRequired<out E>(
            val error: E? = null,
            val headers: Headers? = null
        ) : ClientError<E>()

        data class RequestTimeout<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class Conflict<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class Gone<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class LengthRequired<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class PreconditionFailed<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class PayloadTooLarge<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class URITooLong<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class UnsupportedMediaType<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class RequestedRangeNotSatisfiable<out E>(
            val error: E? = null,
            val headers: Headers? = null
        ) : ClientError<E>()

        data class ExpectationFailed<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class ImATeapot<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class MisdirectedRequest<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class UnprocessableEntity<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class Locked<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class FailedDependency<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class UpgradeRequired<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class PreconditionRequired<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class TooManyRequest<out E>(val error: E? = null, val headers: Headers? = null) :
            ClientError<E>()

        data class RequestHeaderFieldsTooLarge<out E>(
            val error: E? = null,
            val headers: Headers? = null
        ) : ClientError<E>()

        data class UnavailableForLegalReasons<out E>(
            val error: E? = null,
            val headers: Headers? = null
        ) : ClientError<E>()
    }

    sealed class ServerError<out E> : NetworkResponse<Nothing, E>() {
        data class Any<E>(val error: E? = null, val code: Int, val headers: Headers? = null) :
            ServerError<E>()

        data class InternalServerError<out E>(val error: E? = null, val headers: Headers? = null) :
            ServerError<E>()

        data class NotImplemented<out E>(val error: E? = null, val headers: Headers? = null) :
            ServerError<E>()

        data class BadGateway<out E>(val error: E? = null, val headers: Headers? = null) :
            ServerError<E>()

        data class ServiceUnavailable<out E>(val error: E? = null, val headers: Headers? = null) :
            ServerError<E>()

        data class GatewayTimeout<out E>(val error: E? = null, val headers: Headers? = null) :
            ServerError<E>()

        data class HTTPVersionNotSupported<out E>(
            val error: E? = null,
            val headers: Headers? = null
        ) : ServerError<E>()

        data class VariantAlsoNegotiates<out E>(
            val error: E? = null,
            val headers: Headers? = null
        ) : ServerError<E>()

        data class InsufficientStorage<out E>(val error: E? = null, val headers: Headers? = null) :
            ServerError<E>()

        data class LoopDetected<out E>(val error: E? = null, val headers: Headers? = null) :
            ServerError<E>()

        data class NotExtended<out E>(val error: E? = null, val headers: Headers? = null) :
            ServerError<E>()

        data class NetworkAuthenticationRequired<out E>(
            val error: E? = null,
            val headers: Headers? = null
        ) : ServerError<E>()
    }

    data class NonGenericStatus<out NR, out E>(
        val value: NR? = null,
        val error: E? = null,
        val code: Int,
        val headers: Headers? = null
    ) : NetworkResponse<NR, E>()

    data class InternetNotAvailable(val error: String) : NetworkResponse<Nothing, Nothing>()
    data class RemoteError(val error: String) : NetworkResponse<Nothing, Nothing>()
}