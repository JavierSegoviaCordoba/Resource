package com.javiersc.resource.extensions

import com.javiersc.resource.Resource

inline fun <R, reified C : Any, E, reified Er : Any> Resource<R, E>.map(
    mapResource: (R?) -> C,
    mapError: (E?) -> Er?
): Resource<C, Er> {
    return when (this) {
        is Resource.Loading -> Resource.Loading(mapResource(this.resource))
        is Resource.Cache -> Resource.Cache(mapResource(this.resource))
        is Resource.Info.Continue -> Resource.Info.Continue
        is Resource.Info.SwitchingProtocol -> Resource.Info.SwitchingProtocol
        is Resource.Info.Processing -> Resource.Info.Processing
        is Resource.Success.OK -> Resource.Success.OK(mapResource(this.resource))
        is Resource.Success.Created -> Resource.Success.Created(mapResource(this.resource))
        is Resource.Success.Accepted -> Resource.Success.Accepted(mapResource(this.resource))
        is Resource.Success.NonAuthoritativeInformation ->
            Resource.Success.NonAuthoritativeInformation(mapResource(this.resource))
        is Resource.Success.NoContent -> Resource.Success.NoContent
        is Resource.Success.ResetContent -> Resource.Success.ResetContent
        is Resource.Success.PartialContent ->
            Resource.Success.PartialContent(mapResource(this.resource))
        is Resource.Success.MultiStatus ->
            Resource.Success.MultiStatus(mapResource(this.resource))
        is Resource.Success.AlreadyReported ->
            Resource.Success.AlreadyReported(mapResource(this.resource))
        is Resource.Success.ImUsed -> Resource.Success.ImUsed(mapResource(this.resource))
        is Resource.Redirection.MultipleChoices -> Resource.Redirection.MultipleChoices
        is Resource.Redirection.MovedPermanently -> Resource.Redirection.MovedPermanently
        is Resource.Redirection.Found -> Resource.Redirection.Found
        is Resource.Redirection.SeeOther -> Resource.Redirection.SeeOther
        is Resource.Redirection.NotModified -> Resource.Redirection.NotModified
        is Resource.Redirection.UseProxy -> Resource.Redirection.UseProxy
        is Resource.Redirection.SwitchProxy -> Resource.Redirection.SwitchProxy
        is Resource.Redirection.TemporaryRedirect -> Resource.Redirection.TemporaryRedirect
        is Resource.Redirection.PermanentRedirect -> Resource.Redirection.PermanentRedirect
        is Resource.ClientError.BadRequest ->
            Resource.ClientError.BadRequest(mapError(this.error))
        is Resource.ClientError.Unauthorized ->
            Resource.ClientError.Unauthorized(mapError(this.error))
        is Resource.ClientError.PaymentRequired ->
            Resource.ClientError.PaymentRequired(mapError(this.error))
        is Resource.ClientError.Forbidden -> Resource.ClientError.Forbidden(mapError(this.error))
        is Resource.ClientError.NotFound -> Resource.ClientError.NotFound(mapError(this.error))
        is Resource.ClientError.MethodNotAllowed ->
            Resource.ClientError.MethodNotAllowed(mapError(this.error))
        is Resource.ClientError.NotAcceptable ->
            Resource.ClientError.NotAcceptable(mapError(this.error))
        is Resource.ClientError.ProxyAuthenticationRequired ->
            Resource.ClientError.ProxyAuthenticationRequired(mapError(this.error))
        is Resource.ClientError.RequestTimeout ->
            Resource.ClientError.RequestTimeout(mapError(this.error))
        is Resource.ClientError.Conflict -> Resource.ClientError.Conflict(mapError(this.error))
        is Resource.ClientError.Gone -> Resource.ClientError.Gone(mapError(this.error))
        is Resource.ClientError.LengthRequired ->
            Resource.ClientError.LengthRequired(mapError(this.error))
        is Resource.ClientError.PreconditionFailed ->
            Resource.ClientError.PreconditionFailed(mapError(this.error))
        is Resource.ClientError.PayloadTooLarge ->
            Resource.ClientError.PayloadTooLarge(mapError(this.error))
        is Resource.ClientError.URITooLong ->
            Resource.ClientError.URITooLong(mapError(this.error))
        is Resource.ClientError.UnsupportedMediaType ->
            Resource.ClientError.UnsupportedMediaType(mapError(this.error))
        is Resource.ClientError.RequestedRangeNotSatisfiable ->
            Resource.ClientError.RequestedRangeNotSatisfiable(mapError(this.error))
        is Resource.ClientError.ExpectationFailed ->
            Resource.ClientError.ExpectationFailed(mapError(this.error))
        is Resource.ClientError.ImATeapot -> Resource.ClientError.ImATeapot(mapError(this.error))
        is Resource.ClientError.MisdirectedRequest ->
            Resource.ClientError.MisdirectedRequest(mapError(this.error))
        is Resource.ClientError.UnprocessableEntity ->
            Resource.ClientError.UnprocessableEntity(mapError(this.error))
        is Resource.ClientError.Locked -> Resource.ClientError.Locked(mapError(this.error))
        is Resource.ClientError.FailedDependency ->
            Resource.ClientError.FailedDependency(mapError(this.error))
        is Resource.ClientError.UpgradeRequired ->
            Resource.ClientError.UpgradeRequired(mapError(this.error))
        is Resource.ClientError.PreconditionRequired ->
            Resource.ClientError.PreconditionRequired(mapError(this.error))
        is Resource.ClientError.TooManyRequest ->
            Resource.ClientError.TooManyRequest(mapError(this.error))
        is Resource.ClientError.RequestHeaderFieldsTooLarge ->
            Resource.ClientError.RequestHeaderFieldsTooLarge(mapError(this.error))
        is Resource.ClientError.UnavailableForLegalReasons ->
            Resource.ClientError.UnavailableForLegalReasons(mapError(this.error))
        is Resource.ServerError.InternalServerError ->
            Resource.ServerError.InternalServerError(mapError(this.error))
        is Resource.ServerError.NotImplemented ->
            Resource.ServerError.NotImplemented(mapError(this.error))
        is Resource.ServerError.BadGateway ->
            Resource.ServerError.BadGateway(mapError(this.error))
        is Resource.ServerError.ServiceUnavailable ->
            Resource.ServerError.ServiceUnavailable(mapError(this.error))
        is Resource.ServerError.GatewayTimeout ->
            Resource.ServerError.GatewayTimeout(mapError(this.error))
        is Resource.ServerError.HTTPVersionNotSupported ->
            Resource.ServerError.HTTPVersionNotSupported(mapError(this.error))
        is Resource.ServerError.VariantAlsoNegotiates ->
            Resource.ServerError.VariantAlsoNegotiates(mapError(this.error))
        is Resource.ServerError.InsufficientStorage ->
            Resource.ServerError.InsufficientStorage(mapError(this.error))
        is Resource.ServerError.LoopDetected ->
            Resource.ServerError.LoopDetected(mapError(this.error))
        is Resource.ServerError.NotExtended ->
            Resource.ServerError.NotExtended(mapError(this.error))
        is Resource.ServerError.NetworkAuthenticationRequired ->
            Resource.ServerError.NetworkAuthenticationRequired(mapError(this.error))
        is Resource.NonGenericError -> Resource.NonGenericError(mapError(this.error), this.code)
        is Resource.InternetNotAvailable -> Resource.InternetNotAvailable
        is Resource.RemoteError -> Resource.RemoteError
    }
}

