package com.javiersc.resource.network.extensions

import com.javiersc.resource.Resource
import com.javiersc.resource.network.NetworkResponse

inline fun <
        reified NR : Any,
        reified R : Any,
        reified ErDTO : Any?,
        reified Er : Any
        > NetworkResponse<NR, ErDTO>.toResource(
    crossinline mapSuccess: (NR) -> R,
    noinline mapNonGenericSuccess: ((NR?) -> R)? = null,
    crossinline mapError: (ErDTO?) -> Er,
    noinline mapClientError: ((ErDTO?) -> Er)? = null,
    noinline mapServerError: ((ErDTO?) -> Er)? = null,
    noinline mapBadRequest: ((ErDTO?) -> Er)? = null,
    noinline mapUnauthorized: ((ErDTO?) -> Er)? = null,
    noinline mapPaymentRequired: ((ErDTO?) -> Er)? = null,
    noinline mapForbidden: ((ErDTO?) -> Er)? = null,
    noinline mapNotFound: ((ErDTO?) -> Er)? = null,
    noinline mapMethodNotAllowed: ((ErDTO?) -> Er)? = null,
    noinline mapNotAcceptable: ((ErDTO?) -> Er)? = null,
    noinline mapProxyAuthenticationRequired: ((ErDTO?) -> Er)? = null,
    noinline mapRequestTimeout: ((ErDTO?) -> Er)? = null,
    noinline mapConflict: ((ErDTO?) -> Er)? = null,
    noinline mapGone: ((ErDTO?) -> Er)? = null,
    noinline mapLengthRequired: ((ErDTO?) -> Er)? = null,
    noinline mapPreconditionFailed: ((ErDTO?) -> Er)? = null,
    noinline mapPayloadTooLarge: ((ErDTO?) -> Er)? = null,
    noinline mapURITooLong: ((ErDTO?) -> Er)? = null,
    noinline mapUnsupportedMediaType: ((ErDTO?) -> Er)? = null,
    noinline mapRequestedRangeNotSatisfiable: ((ErDTO?) -> Er)? = null,
    noinline mapExpectationFailed: ((ErDTO?) -> Er)? = null,
    noinline mapImATeapot: ((ErDTO?) -> Er)? = null,
    noinline mapMisdirectedRequest: ((ErDTO?) -> Er)? = null,
    noinline mapUnprocessableEntity: ((ErDTO?) -> Er)? = null,
    noinline mapLocked: ((ErDTO?) -> Er)? = null,
    noinline mapFailedDependency: ((ErDTO?) -> Er)? = null,
    noinline mapUpgradeRequired: ((ErDTO?) -> Er)? = null,
    noinline mapPreconditionRequired: ((ErDTO?) -> Er)? = null,
    noinline mapTooManyRequest: ((ErDTO?) -> Er)? = null,
    noinline mapRequestHeaderFieldsTooLarge: ((ErDTO?) -> Er)? = null,
    noinline mapUnavailableForLegalReasons: ((ErDTO?) -> Er)? = null,
    noinline mapInternalServerError: ((ErDTO?) -> Er)? = null,
    noinline mapNotImplemented: ((ErDTO?) -> Er)? = null,
    noinline mapBadGateway: ((ErDTO?) -> Er)? = null,
    noinline mapServiceUnavailable: ((ErDTO?) -> Er)? = null,
    noinline mapGatewayTimeout: ((ErDTO?) -> Er)? = null,
    noinline mapHTTPVersionNotSupported: ((ErDTO?) -> Er)? = null,
    noinline mapVariantAlsoNegotiates: ((ErDTO?) -> Er)? = null,
    noinline mapInsufficientStorage: ((ErDTO?) -> Er)? = null,
    noinline mapLoopDetected: ((ErDTO?) -> Er)? = null,
    noinline mapNotExtended: ((ErDTO?) -> Er)? = null,
    noinline mapNetworkAuthenticationRequired: ((ErDTO?) -> Er)? = null,
    noinline mapNonGenericError: ((ErDTO?) -> Er)? = null,
    noinline mapInternetNotAvailable: ((String?) -> Er)? = null,
    noinline mapRemoteError: ((String?) -> Er)? = null
): Resource<R, Er> {
    return when (this) {
        is NetworkResponse.Success.OK -> Resource.Success(mapSuccess(value))
        is NetworkResponse.Success.Created -> Resource.Success(mapSuccess(value))
        is NetworkResponse.Success.Accepted -> Resource.Success(mapSuccess(value))
        is NetworkResponse.Success.NonAuthoritativeInformation ->
            Resource.Success(mapSuccess(value))
        is NetworkResponse.Success.NoContent -> Resource.Success.NoData
        is NetworkResponse.Success.ResetContent -> Resource.Success.NoData
        is NetworkResponse.Success.PartialContent -> Resource.Success(mapSuccess(value))
        is NetworkResponse.Success.MultiStatus -> Resource.Success(mapSuccess(value))
        is NetworkResponse.Success.AlreadyReported -> Resource.Success(mapSuccess(value))
        is NetworkResponse.Success.ImUsed -> Resource.Success(mapSuccess(value))
        is NetworkResponse.Success.NonGenericSuccess -> when {
            resource != null ->
                Resource.Success(mapNonGenericSuccess?.invoke(resource) ?: mapSuccess(resource))
            else -> Resource.Success.NoData
        }
        is NetworkResponse.ClientError.BadRequest ->
            Resource.Error(
                mapBadRequest?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.Unauthorized ->
            Resource.Error(
                mapUnauthorized?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.PaymentRequired ->
            Resource.Error(
                mapPaymentRequired?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.Forbidden ->
            Resource.Error(
                mapForbidden?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.NotFound ->
            Resource.Error(
                mapNotFound?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.MethodNotAllowed ->
            Resource.Error(
                mapMethodNotAllowed?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.NotAcceptable ->
            Resource.Error(
                mapNotAcceptable?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.ProxyAuthenticationRequired ->
            Resource.Error(
                mapProxyAuthenticationRequired?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.RequestTimeout ->
            Resource.Error(
                mapRequestTimeout?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.Conflict ->
            Resource.Error(
                mapConflict?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.Gone ->
            Resource.Error(
                mapGone?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.LengthRequired ->
            Resource.Error(
                mapLengthRequired?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.PreconditionFailed ->
            Resource.Error(
                mapPreconditionFailed?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.PayloadTooLarge ->
            Resource.Error(
                mapPayloadTooLarge?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.URITooLong ->
            Resource.Error(
                mapURITooLong?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.UnsupportedMediaType ->
            Resource.Error(
                mapUnsupportedMediaType?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.RequestedRangeNotSatisfiable ->
            Resource.Error(
                mapRequestedRangeNotSatisfiable?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.ExpectationFailed ->
            Resource.Error(
                mapExpectationFailed?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.ImATeapot ->
            Resource.Error(
                mapImATeapot?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.MisdirectedRequest ->
            Resource.Error(
                mapMisdirectedRequest?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.UnprocessableEntity ->
            Resource.Error(
                mapUnprocessableEntity?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.Locked ->
            Resource.Error(
                mapLocked?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.FailedDependency ->
            Resource.Error(
                mapFailedDependency?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.UpgradeRequired ->
            Resource.Error(
                mapUpgradeRequired?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.PreconditionRequired ->
            Resource.Error(
                mapPreconditionRequired?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.TooManyRequest ->
            Resource.Error(
                mapTooManyRequest?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.RequestHeaderFieldsTooLarge ->
            Resource.Error(
                mapRequestHeaderFieldsTooLarge?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ClientError.UnavailableForLegalReasons ->
            Resource.Error(
                mapUnavailableForLegalReasons?.invoke(error)
                    ?: mapClientError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.InternalServerError ->
            Resource.Error(
                mapInternalServerError?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.NotImplemented ->
            Resource.Error(
                mapNotImplemented?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.BadGateway ->
            Resource.Error(
                mapBadGateway?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.ServiceUnavailable ->
            Resource.Error(
                mapServiceUnavailable?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.GatewayTimeout ->
            Resource.Error(
                mapGatewayTimeout?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.HTTPVersionNotSupported ->
            Resource.Error(
                mapHTTPVersionNotSupported?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.VariantAlsoNegotiates ->
            Resource.Error(
                mapVariantAlsoNegotiates?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.InsufficientStorage ->
            Resource.Error(
                mapInsufficientStorage?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.LoopDetected ->
            Resource.Error(
                mapLoopDetected?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.NotExtended ->
            Resource.Error(
                mapNotExtended?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.ServerError.NetworkAuthenticationRequired ->
            Resource.Error(
                mapNetworkAuthenticationRequired?.invoke(error)
                    ?: mapServerError?.invoke(error)
                    ?: mapError(error)
            )
        is NetworkResponse.NonGenericError ->
            Resource.Error(
                mapNonGenericError?.invoke(error) ?: mapError(error)
            )
        is NetworkResponse.InternetNotAvailable ->
            Resource.Error(mapInternetNotAvailable?.invoke(error))
        is NetworkResponse.RemoteError ->
            Resource.Error(mapRemoteError?.invoke(error))
        is NetworkResponse.Info.Any -> TODO()
        is NetworkResponse.Info.Continue -> TODO()
        is NetworkResponse.Info.SwitchingProtocol -> TODO()
        is NetworkResponse.Info.Processing -> TODO()
        is NetworkResponse.Success.Any -> TODO()
        is NetworkResponse.Redirection.Any -> TODO()
        is NetworkResponse.Redirection.MultipleChoices -> TODO()
        is NetworkResponse.Redirection.MovedPermanently -> TODO()
        is NetworkResponse.Redirection.Found -> TODO()
        is NetworkResponse.Redirection.SeeOther -> TODO()
        is NetworkResponse.Redirection.NotModified -> TODO()
        is NetworkResponse.Redirection.UseProxy -> TODO()
        is NetworkResponse.Redirection.SwitchProxy -> TODO()
        is NetworkResponse.Redirection.TemporaryRedirect -> TODO()
        is NetworkResponse.Redirection.PermanentRedirect -> TODO()
        is NetworkResponse.ClientError.Any -> TODO()
        is NetworkResponse.ServerError.Any -> TODO()
    }
}