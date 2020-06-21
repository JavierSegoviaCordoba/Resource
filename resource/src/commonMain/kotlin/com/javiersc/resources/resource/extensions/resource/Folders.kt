package com.javiersc.resources.resource.extensions.resource

import com.javiersc.resources.resource.Resource

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
    noinline noSuccess: (() -> Unit)? = null,
    noinline error: ((E) -> Unit)? = null,
    noinline noError: (() -> Unit)? = null,
) {
    when (this) {
        is Resource.Loading -> {
            loading?.invoke()
            noSuccess?.invoke()
            noError?.invoke()
        }
        is Resource.Success -> {
            success?.invoke(data)
            noLoading?.invoke()
            noError?.invoke()
        }
        is Resource.Error -> {
            error?.invoke(this.error)
            noLoading?.invoke()
            noSuccess?.invoke()
        }
    }
}
