package com.javiersc.resource.extensions

import com.javiersc.resource.Resource


private const val BRIGHT_YELLOW = "\u001B[93m"
private const val BRIGHT_RED = "\u001B[91m"

internal fun printlnWarning(message: String) = println("$BRIGHT_YELLOW$message")
internal fun printlnError(message: String) = println("$BRIGHT_RED$message")

inline fun <reified R> R.toResourceSuccess() : Resource.Success<R> {
    return Resource.Success(this)
}

inline fun <reified E> E.toResourceError() : Resource.Error<E> {
    return Resource.Error(this)
}

inline fun <reified R> R.toResourceCache() : Resource.Cache<R> {
    return Resource.Cache(this)
}
