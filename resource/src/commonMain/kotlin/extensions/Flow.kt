package com.javiersc.resource.extensions

import com.javiersc.resource.Resource
import com.javiersc.resource.extensions.resource.combine
import com.javiersc.resource.extensions.resource.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map

/**
 * Map a flow of resources to another object, for example a ScreenState sealed class
 * @param S is the success type
 * @param S is the error type
 * @param T is the new object type
 */
public fun <S, E, T> Flow<Resource<S, E>>.map(loading: () -> T, success: (S) -> T, error: (E) -> T): Flow<T> {
    return map { resource ->
        resource.map(loading = loading, success = success, error = error)
    }
}

/**
 * Transform any flow to a Success flow.
 * @param S is the object type to be wrapped in the Resource flow.
 */
public fun <S> Flow<S>.asSuccess(): Flow<Resource.Success<S>> {
    return map { resource: S -> Resource.Success(resource) }
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
public inline fun <reified S1, reified S2, reified S, reified E> Flow<Resource<S1, E>>.combine(
    resourceFlow: Flow<Resource<S2, E>>,
    crossinline combine: (S1, S2) -> S,
): Flow<Resource<S, E>> {
    return this.combine(resourceFlow) { resourceR, resourceR2 ->
        resourceR.combine(resourceR2) { r, r2 -> combine(r, r2) }
    }
}

/**
 * Combine and transform two Resource flows with the same error type into one
 */
public inline fun <reified S1, reified S2, reified S, reified E> Flow<Resource<S1, E>>.combineTransform(
    resourceFlow: Flow<Resource<S2, E>>,
    crossinline transform: suspend (S1, S2) -> Flow<S>,
): Flow<S> {
    return this@combineTransform.combineTransform(resourceFlow) { resourceR, resourceR2 ->
        resourceR.combine(resourceR2) { r, r2 -> emitAll(transform(r, r2)) }
    }
}
