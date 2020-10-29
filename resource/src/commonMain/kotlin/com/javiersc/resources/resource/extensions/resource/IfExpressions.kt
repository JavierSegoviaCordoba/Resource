package com.javiersc.resources.resource.extensions.resource

import com.javiersc.resources.resource.Resource

/**
 * Extension function with a callback which is invoked if Resource is Loading.
 */
public fun <R, E> Resource<R, E>.ifLoading(block: () -> Unit) {
    if (this is Resource.Loading) block()
}

/**
 * Extension function with a callback which is invoked if Resource is not Loading.
 */
public fun <R, E> Resource<R, E>.ifNoLoading(block: () -> Unit) {
    if (this !is Resource.Loading) block()
}

/**
 * Extension function with a callback which is invoked if Resource is Success and has data.
 */
public fun <R, E> Resource<R, E>.ifSuccess(block: (R) -> Unit) {
    if (this is Resource.Success) block(data)
}

/**
 * Extension function with a callback which is invoked if Resource is not Success.
 */
public fun <R, E> Resource<R, E>.ifNoSuccess(block: () -> Unit) {
    if (this !is Resource.Success) block()
}

/**
 * Extension function with a callback which is invoked if Resource is Error and has error data.
 */
public fun <R, E> Resource<R, E>.ifError(block: (E) -> Unit) {
    if (this is Resource.Error) block(error)
}

/**
 * Extension function with a callback which is invoked if Resource is not Error.
 */
public fun <R, E> Resource<R, E>.ifNoError(block: () -> Unit) {
    if (this !is Resource.Error) block()
}
