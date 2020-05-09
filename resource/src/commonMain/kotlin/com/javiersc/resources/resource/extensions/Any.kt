package com.javiersc.resources.resource.extensions

import com.javiersc.resources.resource.Resource

/**
 * Transform any thing to Success.
 * @param R is the object type to be wrapped in a Resource.
 */
inline fun <reified R> R.toResourceSuccess(): Resource.Success<R> {
    return Resource.Success(this)
}

/**
 * Transform any thing to Error.
 * @param E is the object type to be wrapped in a Resource.
 */
inline fun <reified E> E.toResourceError(): Resource.Error<E> {
    return Resource.Error(this)
}
