@file:Suppress("TooManyFunctions")

package com.javiersc.resources.resource.extensions

import com.javiersc.resources.resource.Resource

/**
 * Map any Resource to another Resource.
 * @param R is the original resource type.
 * @param E is the original error type.
 * @param R2 the new resource type.
 * @param E2 the new error type.
 * @param data to be mapped to [R2].
 * @param error to be mapped to [E2].
 */
inline fun <reified R, reified R2, reified E, reified E2> Resource<R, E>.map(
    data: (R?) -> R2,
    error: (E?) -> E2?,
): Resource<R2, E2> {
    return when (this) {
        is Resource.Loading -> Resource.Loading
        is Resource.Cache -> Resource.Cache(data(this.data))
        is Resource.Success -> Resource.Success(data(this.data))
        is Resource.Error -> Resource.Error(error(this.error))
    }
}

/**
 * Extension function to fold a Resource.
 * Check Resource.Folder inner class to see all the available options.
 */
inline fun <reified R, E> Resource<R, E>.folder(block: Resource<R, E>.Folder.() -> Unit) {
    Folder(this).apply(block)
}

/**
 * Extension function to fold a Resource without builder.
 */
@Suppress("LongParameterList")
inline fun <reified R, E> Resource<R, E>.fold(
    noinline loading: (() -> Unit)? = null,
    noinline noLoading: (() -> Unit)? = null,
    noinline success: ((R) -> Unit)? = null,
    noinline successEmpty: (() -> Unit)? = null,
    noinline noSuccess: (() -> Unit)? = null,
    noinline error: ((E) -> Unit)? = null,
    noinline errorEmpty: (() -> Unit)? = null,
    noinline noError: (() -> Unit)? = null,
    noinline cache: ((R) -> Unit)? = null,
    noinline cacheEmpty: (() -> Unit)? = null,
    noinline noCache: (() -> Unit)? = null,
) {
    when (this) {
        is Resource.Loading -> {
            loading?.invoke()
            noSuccess?.invoke()
            noError?.invoke()
            noCache?.invoke()
        }
        is Resource.Success -> {
            if (data != null) success?.invoke(data) else successEmpty?.invoke()
            noLoading?.invoke()
            noError?.invoke()
            noCache?.invoke()
        }
        is Resource.Error -> {
            if (this.error != null) error?.invoke(this.error) else errorEmpty?.invoke()
            noLoading?.invoke()
            noSuccess?.invoke()
            noCache?.invoke()
        }
        is Resource.Cache -> {
            if (data != null) cache?.invoke(data) else cacheEmpty?.invoke()
            noLoading?.invoke()
            noSuccess?.invoke()
            noError?.invoke()
        }
    }
}

/**
 * Extension function with a callback which is invoked if Resource is Loading.
 */
inline fun <reified R, reified E> Resource<R, E>.ifLoading(block: () -> Unit) {
    if (this is Resource.Loading) block()
}

/**
 * Extension function with a callback which is invoked if Resource is not Loading.
 */
inline fun <reified R, reified E> Resource<R, E>.ifNoLoading(block: () -> Unit) {
    if (this !is Resource.Loading) block()
}

/**
 * Extension function with a callback which is invoked if Resource is Success and has data.
 */
inline fun <reified R, reified E> Resource<R, E>.ifSuccess(block: (R) -> Unit) {
    if (this is Resource.Success && data != null) block(data)
}

/**
 * Extension function with a callback which is invoked if Resource is Success and has no data.
 */
inline fun <reified R, reified E> Resource<R, E>.ifSuccessEmpty(block: () -> Unit) {
    if (this is Resource.Success && data == null) block()
}

/**
 * Extension function with a callback which is invoked if Resource is not Success.
 */
inline fun <reified R, reified E> Resource<R, E>.ifNoSuccess(block: () -> Unit) {
    if (this !is Resource.Success) block()
}

/**
 * Extension function with a callback which is invoked if Resource is Error and has error data.
 */
inline fun <reified R, reified E> Resource<R, E>.ifError(block: (E) -> Unit) {
    if (this is Resource.Error && error != null) block(error)
}

/**
 * Extension function with a callback which is invoked if Resource is Error and has no error data.
 */
inline fun <reified R, reified E> Resource<R, E>.ifErrorEmpty(block: () -> Unit) {
    if (this is Resource.Error && error == null) block()
}

/**
 * Extension function with a callback which is invoked if Resource is not Error.
 */
inline fun <reified R, reified E> Resource<R, E>.ifNoError(block: () -> Unit) {
    if (this !is Resource.Error) block()
}

/**
 * Extension function with a callback which is invoked if Resource is Cache and has data.
 */
inline fun <reified R, reified E> Resource<R, E>.ifCache(block: (R) -> Unit) {
    if (this is Resource.Cache && data != null) block(data)
}

/**
 * Extension function with a callback which is invoked if Resource is Cache and has no data.
 */
inline fun <reified R, reified E> Resource<R, E>.ifCacheEmpty(block: () -> Unit) {
    if (this is Resource.Cache && data == null) block()
}

/**
 * Extension function with a callback which is invoked if Resource is not Cache.
 */
inline fun <reified R, reified E> Resource<R, E>.ifNoCache(block: () -> Unit) {
    if (this !is Resource.Cache) block()
}
