package com.javiersc.resource

sealed class Resource<out R, out E> {

    data class Loading<out R>(val resource: R? = null) : Resource<R, Nothing>()

    data class Cache<out R>(val resource: R?) : Resource<R, Nothing>()

    sealed class Info : Resource<Nothing, Nothing>() {
        object Continue : Info()
        object SwitchingProtocol : Info()
        object Processing : Info()
    }

    sealed class Success<out R> : Resource<R, Nothing>() {
        data class OK<out R>(val resource: R) : Success<R>()
        data class Created<out R>(val resource: R) : Success<R>()
        data class Accepted<out R>(val resource: R) : Success<R>()
        data class NonAuthoritativeInformation<out R>(val resource: R) : Success<R>()
        object NoContent : Success<Nothing>()
        object ResetContent : Success<Nothing>()
        data class PartialContent<out R>(val resource: R) : Success<R>()
        data class MultiStatus<out R>(val resource: R) : Success<R>()
        data class AlreadyReported<out R>(val resource: R) : Success<R>()
        data class ImUsed<out R>(val resource: R) : Success<R>()
    }

    sealed class Redirection : Resource<Nothing, Nothing>() {
        object MultipleChoices : Redirection()
        object MovedPermanently : Redirection()
        object Found : Redirection()
        object SeeOther : Redirection()
        object NotModified : Redirection()
        object UseProxy : Redirection()
        object SwitchProxy : Redirection()
        object TemporaryRedirect : Redirection()
        object PermanentRedirect : Redirection()
    }

    sealed class ClientError<out E> : Resource<Nothing, E>() {
        data class BadRequest<out E>(val error: E?) : ClientError<E>()
        data class Unauthorized<out E>(val error: E?) : ClientError<E>()
        data class PaymentRequired<out E>(val error: E?) : ClientError<E>()
        data class Forbidden<out E>(val error: E?) : ClientError<E>()
        data class NotFound<out E>(val error: E?) : ClientError<E>()
        data class MethodNotAllowed<out E>(val error: E?) : ClientError<E>()
        data class NotAcceptable<out E>(val error: E?) : ClientError<E>()
        data class ProxyAuthenticationRequired<out E>(val error: E?) : ClientError<E>()
        data class RequestTimeout<out E>(val error: E?) : ClientError<E>()
        data class Conflict<out E>(val error: E?) : ClientError<E>()
        data class Gone<out E>(val error: E?) : ClientError<E>()
        data class LengthRequired<out E>(val error: E?) : ClientError<E>()
        data class PreconditionFailed<out E>(val error: E?) : ClientError<E>()
        data class PayloadTooLarge<out E>(val error: E?) : ClientError<E>()
        data class URITooLong<out E>(val error: E?) : ClientError<E>()
        data class UnsupportedMediaType<out E>(val error: E?) : ClientError<E>()
        data class RequestedRangeNotSatisfiable<out E>(val error: E?) : ClientError<E>()
        data class ExpectationFailed<out E>(val error: E?) : ClientError<E>()
        data class ImATeapot<out E>(val error: E?) : ClientError<E>()
        data class MisdirectedRequest<out E>(val error: E?) : ClientError<E>()
        data class UnprocessableEntity<out E>(val error: E?) : ClientError<E>()
        data class Locked<out E>(val error: E?) : ClientError<E>()
        data class FailedDependency<out E>(val error: E?) : ClientError<E>()
        data class UpgradeRequired<out E>(val error: E?) : ClientError<E>()
        data class PreconditionRequired<out E>(val error: E?) : ClientError<E>()
        data class TooManyRequest<out E>(val error: E?) : ClientError<E>()
        data class RequestHeaderFieldsTooLarge<out E>(val error: E?) : ClientError<E>()
        data class UnavailableForLegalReasons<out E>(val error: E?) : ClientError<E>()
    }

    sealed class ServerError<out E> : Resource<Nothing, E>() {
        data class InternalServerError<out E>(val error: E?) : ServerError<E>()
        data class NotImplemented<out E>(val error: E?) : ServerError<E>()
        data class BadGateway<out E>(val error: E?) : ServerError<E>()
        data class ServiceUnavailable<out E>(val error: E?) : ServerError<E>()
        data class GatewayTimeout<out E>(val error: E?) : ServerError<E>()
        data class HTTPVersionNotSupported<out E>(val error: E?) : ServerError<E>()
        data class VariantAlsoNegotiates<out E>(val error: E?) : ServerError<E>()
        data class InsufficientStorage<out E>(val error: E?) : ServerError<E>()
        data class LoopDetected<out E>(val error: E?) : ServerError<E>()
        data class NotExtended<out E>(val error: E?) : ServerError<E>()
        data class NetworkAuthenticationRequired<out E>(val error: E?) : ServerError<E>()
    }

    data class NonGenericError<out E>(val error: E?, val code: Int) : Resource<Nothing, E>()

    object InternetNotAvailable : Resource<Nothing, Nothing>()
    object RemoteError : Resource<Nothing, Nothing>()
}