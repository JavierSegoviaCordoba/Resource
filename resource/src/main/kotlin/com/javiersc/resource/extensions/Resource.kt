package com.javiersc.resource.extensions

import com.javiersc.resource.Resource

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
    if (isLoading) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifSuccess(block: () -> Unit) {
    if (isSuccess) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifError(block: () -> Unit) {
    if (isError) block()
}

inline fun <reified R, reified E> Resource<R, E>.ifCache(block: () -> Unit) {
    if (isCache) block()
}