package com.javiersc.resources.resource.extensions

import com.javiersc.resources.resource.Resource


inline fun <reified R> R.toResourceSuccess() : Resource.Success<R> {
    return Resource.Success(this)
}

inline fun <reified E> E.toResourceError() : Resource.Error<E> {
    return Resource.Error(this)
}

inline fun <reified R> R.toResourceCache() : Resource.Cache<R> {
    return Resource.Cache(this)
}
