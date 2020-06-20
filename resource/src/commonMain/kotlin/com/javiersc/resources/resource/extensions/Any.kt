package com.javiersc.resources.resource.extensions

import com.javiersc.resources.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Transform any thing to Success.
 * @param R is the object type to be wrapped in a Resource.
 */
inline fun <reified R> R.asSuccess(): Resource.Success<R> = Resource.Success(this)

/**
 * Transform any thing to Error.
 * @param E is the object type to be wrapped in a Resource.
 */
inline fun <reified E> E.asError(): Resource.Error<E> = Resource.Error(this)

/**
 * Transform any thing to Success Flow.
 * @param R is the object type to be wrapped in a Resource.
 */
inline fun <reified R> R.asSuccessFlow(): Flow<Resource.Success<R>> = flow { emit(this@asSuccessFlow.asSuccess()) }

/**
 * Transform any thing to Error.
 * @param E is the object type to be wrapped in a Resource.
 */
inline fun <reified E> E.asErrorFlow(): Flow<Resource.Error<E>> = flow { emit(this@asErrorFlow.asError()) }
