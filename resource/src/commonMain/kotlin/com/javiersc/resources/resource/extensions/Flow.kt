package com.javiersc.resources.resource.extensions

import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.extensions.resource.combine
import com.javiersc.resources.resource.extensions.resource.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map

/**
 * Map a flow of resources to another object, for example a ScreenState sealed class
 * @param R is the success type
 * @param R is the error type
 * @param T is the new object type
 */
public fun <R, E, T> Flow<Resource<R, E>>.map(loading: () -> T, success: (R) -> T, error: (E) -> T): Flow<T> {
    return map { resource ->
        resource.map(loading = loading, success = success, error = error)
    }
}

/**
 * Transform any flow to a Success flow.
 * @param R is the object type to be wrapped in the Resource flow.
 */
public fun <R> Flow<R>.asSuccess(): Flow<Resource.Success<R>> {
    return map { resource: R -> Resource.Success(resource) }
}

/**
 * Transform any flow to an Error flow.
 * @param E is the object type to be wrapped in the Resource flow.
 */
public fun <E> Flow<E>.asError(): Flow<Resource.Error<E>> {
    return map { error: E -> Resource.Error(error) }
}

/**
 * Combine two Resource flows with the same error type into one
 */
public inline fun <reified R, reified R2, reified R3, reified E> Flow<Resource<R, E>>.combine(
    resourceFlow: Flow<Resource<R2, E>>,
    crossinline combine: (R, R2) -> R3,
): Flow<Resource<R3, E>> {
    return this.combine(resourceFlow) { resourceR, resourceR2 ->
        resourceR.combine(resourceR2) { r, r2 -> combine(r, r2) }
    }
}

/**
 * Combine and transform two Resource flows with the same error type into one
 */
public inline fun <reified R, reified R2, reified R3, reified E> Flow<Resource<R, E>>.combineTransform(
    resourceFlow: Flow<Resource<R2, E>>,
    crossinline transform: suspend (R, R2) -> Flow<R3>,
): Flow<R3> {
    return this@combineTransform.combineTransform(resourceFlow) { resourceR, resourceR2 ->
        resourceR.combine(resourceR2) { r, r2 -> emitAll(transform(r, r2)) }
    }
}
