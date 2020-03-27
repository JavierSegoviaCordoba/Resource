package com.javiersc.resources.resource.extensions

import com.javiersc.resources.resource.Resource

inline fun <reified R, reified C, reified E, reified Er> Resource<R, E>.map(
    mapResource: (R?) -> C,
    mapError: (E?) -> Er?
): Resource<C, Er> {
    return when (this) {
        is Resource.Loading -> Resource.Loading
        is Resource.Cache -> Resource.Cache(mapResource(this.data))
        is Resource.Success -> Resource.Success(mapResource(this.data))
        is Resource.Error -> Resource.Error(mapError(this.error))
    }
}

inline fun <reified R, E> Resource<R, E>.fold(block: Resource<R, E>.Folder.() -> Unit) {
    Folder(this).apply(block)
}

inline fun <reified R, reified E> Resource<R, E>.ifLoading(block: () -> Unit) {
    if (this is Resource.Loading) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifNoLoading(block: () -> Unit) {
    if (this !is Resource.Loading) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifSuccess(block: (R) -> Unit) {
    if (this is Resource.Success && data != null) block(data)
}

inline fun <reified R, reified E> Resource<R, E>.ifSuccessEmpty(block: () -> Unit) {
    if (this is Resource.Success && data == null) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifNoSuccess(block: () -> Unit) {
    if (this !is Resource.Success) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifError(block: (E) -> Unit) {
    if (this is Resource.Error && error != null) block(error)
}

inline fun <reified R, reified E> Resource<R, E>.ifErrorEmpty(block: () -> Unit) {
    if (this is Resource.Error && error == null) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifNoError(block: () -> Unit) {
    if (this !is Resource.Error) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifCache(block: (R) -> Unit) {
    if (this is Resource.Cache && data != null) block(data)
}

inline fun <reified R, reified E> Resource<R, E>.ifCacheEmpty(block: () -> Unit) {
    if (this is Resource.Cache && data == null) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifNoCache(block: () -> Unit) {
    if (this !is Resource.Cache) block()
}