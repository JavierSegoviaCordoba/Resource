package com.javiersc.resource.network

import okhttp3.Headers

sealed class NetworkResponse<out R, out E> {

    sealed class Info : NetworkResponse<Nothing, Nothing>() {
        data class Continue(val headers: Headers?) : Info()
        data class SwitchingProtocol(val headers: Headers?) : Info()
        data class Processing(val headers: Headers?) : Info()
    }

    sealed class Success<out R> : NetworkResponse<R, Nothing>() {
        data class OK<out R>(val value: R, val headers: Headers?) : Success<R>()
        data class Created<out R>(val value: R, val headers: Headers?) : Success<R>()
        data class Accepted<out R>(val value: R, val headers: Headers?) : Success<R>()
        data class NonAuthoritativeInformation<out R>(val value: R, val headers: Headers?) :
            Success<R>()

        data class NoContent<out R>(val headers: Headers?) : Success<R>()
        data class ResetContent<out R>(val headers: Headers?) : Success<R>()
        data class PartialContent<out R>(val value: R, val headers: Headers?) : Success<R>()
        data class MultiStatus<out R>(val value: R, val headers: Headers?) : Success<R>()
        data class AlreadyReported<out R>(val value: R, val headers: Headers?) : Success<R>()
        data class ImUsed<out R>(val value: R, val headers: Headers?) : Success<R>()
    }

    sealed class Redirection : NetworkResponse<Nothing, Nothing>() {
        data class MultipleChoices(val headers: Headers?) : Redirection()
        data class MovedPermanently(val headers: Headers?) : Redirection()
        data class Found(val headers: Headers?) : Redirection()
        data class SeeOther(val headers: Headers?) : Redirection()
        data class NotModified(val headers: Headers?) : Redirection()
        data class UseProxy(val headers: Headers?) : Redirection()
        data class SwitchProxy(val headers: Headers?) : Redirection()
        data class TemporaryRedirect(val headers: Headers?) : Redirection()
        data class PermanentRedirect(val headers: Headers?) : Redirection()
    }

    sealed class ClientError<out E> : NetworkResponse<Nothing, E>() {
        data class BadRequest<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class Unauthorized<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class PaymentRequired<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class Forbidden<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class NotFound<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class MethodNotAllowed<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class NotAcceptable<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class ProxyAuthenticationRequired<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()

        data class RequestTimeout<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class Conflict<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class Gone<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class LengthRequired<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class PreconditionFailed<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()

        data class PayloadTooLarge<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class URITooLong<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class UnsupportedMediaType<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()

        data class RequestedRangeNotSatisfiable<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()

        data class ExpectationFailed<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class ImATeapot<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class MisdirectedRequest<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()

        data class UnprocessableEntity<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()

        data class Locked<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class FailedDependency<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class UpgradeRequired<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class PreconditionRequired<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()

        data class TooManyRequest<out E>(val error: E?, val headers: Headers?) : ClientError<E>()
        data class RequestHeaderFieldsTooLarge<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()

        data class UnavailableForLegalReasons<out E>(val error: E?, val headers: Headers?) :
            ClientError<E>()
    }

    sealed class ServerError<out E> : NetworkResponse<Nothing, E>() {
        data class InternalServerError<out E>(val error: E?, val headers: Headers?) :
            ServerError<E>()

        data class NotImplemented<out E>(val error: E?, val headers: Headers?) : ServerError<E>()
        data class BadGateway<out E>(val error: E?, val headers: Headers?) : ServerError<E>()
        data class ServiceUnavailable<out E>(val error: E?, val headers: Headers?) :
            ServerError<E>()

        data class GatewayTimeout<out E>(val error: E?, val headers: Headers?) : ServerError<E>()
        data class HTTPVersionNotSupported<out E>(val error: E?, val headers: Headers?) :
            ServerError<E>()

        data class VariantAlsoNegotiates<out E>(val error: E?, val headers: Headers?) :
            ServerError<E>()

        data class InsufficientStorage<out E>(val error: E?, val headers: Headers?) :
            ServerError<E>()

        data class LoopDetected<out E>(val error: E?, val headers: Headers?) : ServerError<E>()
        data class NotExtended<out E>(val error: E?, val headers: Headers?) : ServerError<E>()
        data class NetworkAuthenticationRequired<out E>(val error: E?, val headers: Headers?) :
            ServerError<E>()
    }

    data class NonGenericError<out E>(val error: E?, val code: Int, val headers: Headers?) :
        NetworkResponse<Nothing, E>()

    data class InternetNotAvailable(val error: String) : NetworkResponse<Nothing, Nothing>()
    data class RemoteError(val error: String) : NetworkResponse<Nothing, Nothing>()
}