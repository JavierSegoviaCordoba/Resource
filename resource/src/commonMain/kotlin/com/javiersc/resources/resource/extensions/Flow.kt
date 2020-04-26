package com.javiersc.resources.resource.extensions

import com.javiersc.resources.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Transform any flow to a Success flow.
 * @param R is the object type to be wrapped in the Resource flow.
 */
inline fun <reified R : Any> Flow<R>.toResourceSuccess(): Flow<Resource.Success<R>> {
    return map { resource: R -> Resource.Success(resource) }
}

/**
 * Transform any flow to an Error flow.
 * @param E is the object type to be wrapped in the Resource flow.
 */
inline fun <reified E> Flow<E>.toResourceError(): Flow<Resource.Error<E>> {
    return map { error: E -> Resource.Error(error) }
}

/**
 * Transform any flow to a Cache flow.
 * @param R is the object type to be wrapped in the Resource flow.
 */
inline fun <reified R> Flow<R>.toResourceCache(): Flow<Resource.Cache<R>> {
    return map { resource: R -> Resource.Cache(resource) }
}
