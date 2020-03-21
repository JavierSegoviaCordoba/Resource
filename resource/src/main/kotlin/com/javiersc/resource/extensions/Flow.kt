package com.javiersc.resource.extensions

import com.javiersc.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <reified R : Any> Flow<R>.toResourceSuccess(): Flow<Resource.Success<R>> {
    return map { resource: R -> Resource.Success(resource) }
}

inline fun <reified Er> Flow<Er>.toResourceError(): Flow<Resource.Error<Er>> {
    return map { error: Er -> Resource.Error(error) }
}

inline fun <reified R> Flow<R>.toResourceCache(): Flow<Resource.Cache<R>> {
    return map { resource: R -> Resource.Cache(resource) }
}
