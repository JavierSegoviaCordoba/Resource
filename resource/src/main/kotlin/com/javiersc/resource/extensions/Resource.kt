package com.javiersc.resource.extensions

import com.javiersc.resource.Resource

inline fun <R, reified C : Any, E, reified Er : Any> Resource<R, E>.map(
    mapResource: (R?) -> C,
    mapError: (E?) -> Er?
): Resource<C, Er> {
    return when (this) {
        is Resource.Loading -> Resource.Loading(mapResource(this.resource))
        is Resource.Cache -> Resource.Cache(mapResource(this.resource))
        is Resource.Success -> Resource.Success(mapResource(this.resource))
        is Resource.Success.NoData -> Resource.Success.NoData
        is Resource.Error -> Resource.Error(mapError(this.error))
    }
}

