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
    error: (E?) -> E2?
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
 * Check Resource.Folder inner class to see all the option availables.
 */
inline fun <reified R, E> Resource<R, E>.fold(block: Resource<R, E>.Folder.() -> Unit) {
    Folder(this).apply(block)
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
