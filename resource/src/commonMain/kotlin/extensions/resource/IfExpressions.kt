package com.javiersc.resource.extensions.resource

import com.javiersc.resource.Resource

/**
 * Extension function with a callback which is invoked if Resource is Loading.
 */
public fun <S, E> Resource<S, E>.ifLoading(block: () -> Unit) {
    if (this is Resource.Loading) block()
}

/**
 * Extension function with a callback which is invoked if Resource is not Loading.
 */
public fun <S, E> Resource<S, E>.ifNoLoading(block: () -> Unit) {
    if (this !is Resource.Loading) block()
}

/**
 * Extension function with a callback which is invoked if Resource is Success and has data.
 */
public fun <S, E> Resource<S, E>.ifSuccess(block: (S) -> Unit) {
    if (this is Resource.Success) block(data)
}

/**
 * Extension function with a callback which is invoked if Resource is not Success.
 */
public fun <S, E> Resource<S, E>.ifNoSuccess(block: () -> Unit) {
    if (this !is Resource.Success) block()
}

/**
 * Extension function with a callback which is invoked if Resource is Error and has error data.
 */
public fun <S, E> Resource<S, E>.ifError(block: (E) -> Unit) {
    if (this is Resource.Error) block(error)
}

/**
 * Extension function with a callback which is invoked if Resource is not Error.
 */
public fun <S, E> Resource<S, E>.ifNoError(block: () -> Unit) {
    if (this !is Resource.Error) block()
}
