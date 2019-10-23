package com.javiersc.resource.extensions

import com.javiersc.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <reified R : Any, Er : Any> Flow<R>.toResource(
    crossinline mapResource: (R) -> Resource<R, Er>
): Flow<Resource<R, Er>> = map { resource: R -> mapResource(resource) }

inline fun <reified R : Any, Er : Any> Flow<R>.toResourceCache(): Flow<Resource<R, Er>> {
    return map { resource: R -> Resource.Cache(resource) }
}
