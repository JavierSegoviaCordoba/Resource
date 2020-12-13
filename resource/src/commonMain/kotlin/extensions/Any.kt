package com.javiersc.resource.extensions

import com.javiersc.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Transform any thing to Success.
 * @param S is the object type to be wrapped in a Resource.
 */
public fun <S> S.asSuccess(): Resource.Success<S> = Resource.Success(this)

/**
 * Transform any thing to Error.
 * @param E is the object type to be wrapped in a Resource.
 */
public fun <E> E.asError(): Resource.Error<E> = Resource.Error(this)

/**
 * Transform any thing to Success Flow.
 * @param S is the object type to be wrapped in a Resource.
 */
public fun <S> S.asSuccessFlow(): Flow<Resource.Success<S>> = flowOf(asSuccess())

/**
 * Transform any thing to Error.
 * @param E is the object type to be wrapped in a Resource.
 */
public fun <E> E.asErrorFlow(): Flow<Resource.Error<E>> = flowOf(asError())
