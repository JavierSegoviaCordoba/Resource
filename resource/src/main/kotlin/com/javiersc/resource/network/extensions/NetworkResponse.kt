package com.javiersc.resource.network.extensions

import com.javiersc.resource.Resource
import com.javiersc.resource.network.NetworkResponse

inline fun <NR, reified R : Any, ErDTO, reified Er : Any> NetworkResponse<NR, ErDTO>.toResource(
    crossinline mapResponse: (NR) -> R,
    crossinline mapError: (ErDTO) -> Er
): Resource<R, Er> {
    return when (this) {
        is NetworkResponse.Info.Continue -> Resource.Info.Continue
        is NetworkResponse.Info.SwitchingProtocol -> Resource.Info.SwitchingProtocol
        is NetworkResponse.Info.Processing -> Resource.Info.Processing
        is NetworkResponse.Success.OK -> Resource.Success.OK(mapResponse(value))
        is NetworkResponse.Success.Created -> Resource.Success.Created(mapResponse(value))
        is NetworkResponse.Success.Accepted -> Resource.Success.Accepted(mapResponse(value))
        is NetworkResponse.Success.NonAuthoritativeInformation ->
            Resource.Success.NonAuthoritativeInformation(mapResponse(value))
        is NetworkResponse.Success.NoContent -> Resource.Success.NoContent
        is NetworkResponse.Success.ResetContent -> Resource.Success.ResetContent
        is NetworkResponse.Success.PartialContent ->
            Resource.Success.PartialContent(mapResponse(value))
        is NetworkResponse.Success.MultiStatus ->
            Resource.Success.MultiStatus(mapResponse(value))
        is NetworkResponse.Success.AlreadyReported ->
            Resource.Success.AlreadyReported(mapResponse(value))
        is NetworkResponse.Success.ImUsed -> Resource.Success.ImUsed(mapResponse(value))
        is NetworkResponse.Redirection.MultipleChoices -> Resource.Redirection.MultipleChoices
        is NetworkResponse.Redirection.MovedPermanently -> Resource.Redirection.MovedPermanently
        is NetworkResponse.Redirection.Found -> Resource.Redirection.Found
        is NetworkResponse.Redirection.SeeOther -> Resource.Redirection.SeeOther
        is NetworkResponse.Redirection.NotModified -> Resource.Redirection.NotModified
        is NetworkResponse.Redirection.UseProxy -> Resource.Redirection.UseProxy
        is NetworkResponse.Redirection.SwitchProxy -> Resource.Redirection.SwitchProxy
        is NetworkResponse.Redirection.TemporaryRedirect -> Resource.Redirection.TemporaryRedirect
        is NetworkResponse.Redirection.PermanentRedirect -> Resource.Redirection.PermanentRedirect
        is NetworkResponse.ClientError.BadRequest ->
            Resource.ClientError.BadRequest(error?.let { mapError(it) })
        is NetworkResponse.ClientError.Unauthorized ->
            Resource.ClientError.Unauthorized(error?.let { mapError(it) })
        is NetworkResponse.ClientError.PaymentRequired ->
            Resource.ClientError.PaymentRequired(error?.let { mapError(it) })
        is NetworkResponse.ClientError.Forbidden ->
            Resource.ClientError.Forbidden(error?.let { mapError(it) })
        is NetworkResponse.ClientError.NotFound ->
            Resource.ClientError.NotFound(error?.let { mapError(it) })
        is NetworkResponse.ClientError.MethodNotAllowed ->
            Resource.ClientError.MethodNotAllowed(error?.let { mapError(it) })
        is NetworkResponse.ClientError.NotAcceptable ->
            Resource.ClientError.NotAcceptable(error?.let { mapError(it) })
        is NetworkResponse.ClientError.ProxyAuthenticationRequired ->
            Resource.ClientError.ProxyAuthenticationRequired(error?.let { mapError(it) })
        is NetworkResponse.ClientError.RequestTimeout ->
            Resource.ClientError.RequestTimeout(error?.let { mapError(it) })
        is NetworkResponse.ClientError.Conflict ->
            Resource.ClientError.Conflict(error?.let { mapError(it) })
        is NetworkResponse.ClientError.Gone ->
            Resource.ClientError.Gone(error?.let { mapError(it) })
        is NetworkResponse.ClientError.LengthRequired ->
            Resource.ClientError.LengthRequired(error?.let { mapError(it) })
        is NetworkResponse.ClientError.PreconditionFailed ->
            Resource.ClientError.PreconditionFailed(error?.let { mapError(it) })
        is NetworkResponse.ClientError.PayloadTooLarge ->
            Resource.ClientError.PayloadTooLarge(error?.let { mapError(it) })
        is NetworkResponse.ClientError.URITooLong ->
            Resource.ClientError.URITooLong(error?.let { mapError(it) })
        is NetworkResponse.ClientError.UnsupportedMediaType ->
            Resource.ClientError.UnsupportedMediaType(error?.let { mapError(it) })
        is NetworkResponse.ClientError.RequestedRangeNotSatisfiable ->
            Resource.ClientError.RequestedRangeNotSatisfiable(error?.let { mapError(it) })
        is NetworkResponse.ClientError.ExpectationFailed ->
            Resource.ClientError.ExpectationFailed(error?.let { mapError(it) })
        is NetworkResponse.ClientError.ImATeapot ->
            Resource.ClientError.ImATeapot(error?.let { mapError(it) })
        is NetworkResponse.ClientError.MisdirectedRequest ->
            Resource.ClientError.MisdirectedRequest(error?.let { mapError(it) })
        is NetworkResponse.ClientError.UnprocessableEntity ->
            Resource.ClientError.UnprocessableEntity(error?.let { mapError(it) })
        is NetworkResponse.ClientError.Locked ->
            Resource.ClientError.Locked(error?.let { mapError(it) })
        is NetworkResponse.ClientError.FailedDependency ->
            Resource.ClientError.FailedDependency(error?.let { mapError(it) })
        is NetworkResponse.ClientError.UpgradeRequired ->
            Resource.ClientError.UpgradeRequired(error?.let { mapError(it) })
        is NetworkResponse.ClientError.PreconditionRequired ->
            Resource.ClientError.PreconditionRequired(error?.let { mapError(it) })
        is NetworkResponse.ClientError.TooManyRequest ->
            Resource.ClientError.TooManyRequest(error?.let { mapError(it) })
        is NetworkResponse.ClientError.RequestHeaderFieldsTooLarge ->
            Resource.ClientError.RequestHeaderFieldsTooLarge(error?.let { mapError(it) })
        is NetworkResponse.ClientError.UnavailableForLegalReasons ->
            Resource.ClientError.UnavailableForLegalReasons(error?.let { mapError(it) })
        is NetworkResponse.ServerError.InternalServerError ->
            Resource.ServerError.InternalServerError(error?.let { mapError(it) })
        is NetworkResponse.ServerError.NotImplemented ->
            Resource.ServerError.NotImplemented(error?.let { mapError(it) })
        is NetworkResponse.ServerError.BadGateway ->
            Resource.ServerError.BadGateway(error?.let { mapError(it) })
        is NetworkResponse.ServerError.ServiceUnavailable ->
            Resource.ServerError.ServiceUnavailable(error?.let { mapError(it) })
        is NetworkResponse.ServerError.GatewayTimeout ->
            Resource.ServerError.GatewayTimeout(error?.let { mapError(it) })
        is NetworkResponse.ServerError.HTTPVersionNotSupported ->
            Resource.ServerError.HTTPVersionNotSupported(error?.let { mapError(it) })
        is NetworkResponse.ServerError.VariantAlsoNegotiates ->
            Resource.ServerError.VariantAlsoNegotiates(error?.let { mapError(it) })
        is NetworkResponse.ServerError.InsufficientStorage ->
            Resource.ServerError.InsufficientStorage(error?.let { mapError(it) })
        is NetworkResponse.ServerError.LoopDetected ->
            Resource.ServerError.LoopDetected(error?.let { mapError(it) })
        is NetworkResponse.ServerError.NotExtended ->
            Resource.ServerError.NotExtended(error?.let { mapError(it) })
        is NetworkResponse.ServerError.NetworkAuthenticationRequired ->
            Resource.ServerError.NetworkAuthenticationRequired(error?.let { mapError(it) })
        is NetworkResponse.NonGenericError ->
            Resource.NonGenericError(error?.let { mapError(it) }, code)
        is NetworkResponse.InternetNotAvailable -> Resource.InternetNotAvailable
        is NetworkResponse.RemoteError -> Resource.RemoteError
    }
}