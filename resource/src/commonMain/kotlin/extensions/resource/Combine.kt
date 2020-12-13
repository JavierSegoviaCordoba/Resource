package com.javiersc.resource.extensions.resource

import com.javiersc.resource.Resource
import com.javiersc.resource.Resource.Error
import com.javiersc.resource.Resource.Loading
import com.javiersc.resource.Resource.Success
import com.javiersc.resource.extensions.asError
import com.javiersc.resource.extensions.asSuccess

/**
 * Combine two Resource into one.
 * Success happens when both are success
 * Loading happens when one is Loading and the another is Loading or Success
 * If there is only an error, [defaultError] will be used
 * If both resources are error, [error] will be used or [defaultError] if it is null
 */
@Suppress("ComplexMethod")
public inline fun <reified S1, reified E1, reified R2, reified E2, reified NS, reified NE> Resource<S1, E1>.combine(
    resource: Resource<R2, E2>,
    success: (S1, R2) -> NS,
    noinline error: ((E1, E2) -> NE)? = null,
    defaultError: NE,
): Resource<NS, NE> = when {
    this is Success && resource is Success -> success(this.data, resource.data).asSuccess()
    this is Loading && resource is Loading -> Loading
    this is Error && resource is Error -> (error?.invoke(this.error, resource.error) ?: defaultError).asError()
    this is Error || this is Error -> defaultError.asError()
    this is Loading || this is Success && resource is Loading || resource is Success -> Loading
    else -> throw IllegalStateException("This state should not be possible")
}

/**
 * Combine two Resource into one. Both resources has the same error type.
 * Success happens when both are success
 * Loading happens when one is Loading and the another is Loading or Success
 * If only one resource is error, it will be returned as error
 * If both resources are errors, [this] will be returned as error
 */
public inline fun <reified S1, reified E, reified S2, reified NS> Resource<S1, E>.combine(
    resource: Resource<S2, E>,
    success: (S1, S2) -> NS,
): Resource<NS, E> = when {
    this is Success && resource is Success -> success(this.data, resource.data).asSuccess()
    this is Loading && resource is Loading -> Loading
    this is Loading || this is Success && resource is Loading || resource is Success -> Loading
    this is Error -> this
    resource is Error -> resource
    else -> throw IllegalStateException("This state should not be possible")
}
