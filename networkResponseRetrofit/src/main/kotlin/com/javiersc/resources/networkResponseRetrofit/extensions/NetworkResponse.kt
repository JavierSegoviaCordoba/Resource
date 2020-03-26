package com.javiersc.resources.networkResponseRetrofit.extensions

import com.javiersc.resources.networkResponseRetrofit.NetworkResponse
import com.javiersc.resources.networkResponseRetrofit.NetworkResponse.*
import com.javiersc.resources.networkResponseRetrofit.NetworkResponse.ClientError.*
import com.javiersc.resources.networkResponseRetrofit.NetworkResponse.Redirection.*
import com.javiersc.resources.networkResponseRetrofit.NetworkResponse.ServerError.*
import com.javiersc.resources.networkResponseRetrofit.NetworkResponse.Success.*
import com.javiersc.resources.resource.Resource

inline fun <reified NR : Any,
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
        is OK -> Resource.Success(mapSuccess(value))
        is Created -> Resource.Success(mapSuccess(value))
        is Accepted -> Resource.Success(mapSuccess(value))
        is NonAuthoritativeInformation -> Resource.Success(mapSuccess(value))
        is NoContent -> Resource.Success(null)
        is ResetContent -> Resource.Success(null)
        is PartialContent -> Resource.Success(mapSuccess(value))
        is MultiStatus -> Resource.Success(mapSuccess(value))
        is AlreadyReported -> Resource.Success(mapSuccess(value))
        is ImUsed -> Resource.Success(mapSuccess(value))
        is NonGenericSuccess -> Resource.Success(mapNonGenericSuccess?.invoke(resource))
        is BadRequest -> Resource.Error(
            mapBadRequest?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is Unauthorized -> Resource.Error(
            mapUnauthorized?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is PaymentRequired -> Resource.Error(
            mapPaymentRequired?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is Forbidden -> Resource.Error(
            mapForbidden?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is NotFound -> Resource.Error(
            mapNotFound?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is MethodNotAllowed -> Resource.Error(
            mapMethodNotAllowed?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is NotAcceptable -> Resource.Error(
            mapNotAcceptable?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is ProxyAuthenticationRequired -> Resource.Error(
            mapProxyAuthenticationRequired?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is RequestTimeout -> Resource.Error(
            mapRequestTimeout?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is Conflict -> Resource.Error(
            mapConflict?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is Gone -> Resource.Error(
            mapGone?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is LengthRequired -> Resource.Error(
            mapLengthRequired?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is PreconditionFailed -> Resource.Error(
            mapPreconditionFailed?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is PayloadTooLarge -> Resource.Error(
            mapPayloadTooLarge?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is URITooLong -> Resource.Error(
            mapURITooLong?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is UnsupportedMediaType -> Resource.Error(
            mapUnsupportedMediaType?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is RequestedRangeNotSatisfiable -> Resource.Error(
            mapRequestedRangeNotSatisfiable?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is ExpectationFailed -> Resource.Error(
            mapExpectationFailed?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is ImATeapot -> Resource.Error(
            mapImATeapot?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is MisdirectedRequest -> Resource.Error(
            mapMisdirectedRequest?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is UnprocessableEntity -> Resource.Error(
            mapUnprocessableEntity?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is Locked -> Resource.Error(
            mapLocked?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is FailedDependency -> Resource.Error(
            mapFailedDependency?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is UpgradeRequired -> Resource.Error(
            mapUpgradeRequired?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is PreconditionRequired -> Resource.Error(
            mapPreconditionRequired?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is TooManyRequest -> Resource.Error(
            mapTooManyRequest?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is RequestHeaderFieldsTooLarge -> Resource.Error(
            mapRequestHeaderFieldsTooLarge?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is UnavailableForLegalReasons -> Resource.Error(
            mapUnavailableForLegalReasons?.invoke(error)
                ?: mapClientError?.invoke(error)
                ?: mapError(error)
        )
        is InternalServerError -> Resource.Error(
            mapInternalServerError?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is NotImplemented -> Resource.Error(
            mapNotImplemented?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is BadGateway -> Resource.Error(
            mapBadGateway?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is ServiceUnavailable -> Resource.Error(
            mapServiceUnavailable?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is GatewayTimeout -> Resource.Error(
            mapGatewayTimeout?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is HTTPVersionNotSupported -> Resource.Error(
            mapHTTPVersionNotSupported?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is VariantAlsoNegotiates -> Resource.Error(
            mapVariantAlsoNegotiates?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is InsufficientStorage -> Resource.Error(
            mapInsufficientStorage?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is LoopDetected -> Resource.Error(
            mapLoopDetected?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is NotExtended -> Resource.Error(
            mapNotExtended?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is NetworkAuthenticationRequired -> Resource.Error(
            mapNetworkAuthenticationRequired?.invoke(error)
                ?: mapServerError?.invoke(error)
                ?: mapError(error)
        )
        is NonGenericError -> Resource.Error(mapNonGenericError?.invoke(error) ?: mapError(error))
        is InternetNotAvailable -> Resource.Error(mapInternetNotAvailable?.invoke(error))
        is RemoteError -> Resource.Error(mapRemoteError?.invoke(error))
        is Info.Any -> TODO()
        is Info.Continue -> TODO()
        is Info.SwitchingProtocol -> TODO()
        is Info.Processing -> TODO()
        is Success.Any -> TODO()
        is Redirection.Any -> TODO()
        is MultipleChoices -> TODO()
        is MovedPermanently -> TODO()
        is Found -> TODO()
        is SeeOther -> TODO()
        is NotModified -> TODO()
        is UseProxy -> TODO()
        is SwitchProxy -> TODO()
        is TemporaryRedirect -> TODO()
        is PermanentRedirect -> TODO()
        is ClientError.Any -> TODO()
        is ServerError.Any -> TODO()
    }
}