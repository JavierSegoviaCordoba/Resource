package com.javiersc.resource.extensions.resource

import com.javiersc.resource.Resource

/**
 * Extension function to fold a Resource.
 * Check Resource.Folder inner class to see all the available options.
 */
public fun <S, E> Resource<S, E>.folder(block: Resource<S, E>.Folder.() -> Unit) {
    Folder(this).apply(block)
}

/**
 * Extension function to fold a Resource without builder.
 */
@Suppress("LongParameterList")
public fun <S, E> Resource<S, E>.fold(
    loading: (() -> Unit)? = null,
    noLoading: (() -> Unit)? = null,
    success: ((S) -> Unit)? = null,
    noSuccess: (() -> Unit)? = null,
    error: ((E) -> Unit)? = null,
    noError: (() -> Unit)? = null,
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
