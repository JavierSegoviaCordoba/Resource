package com.javiersc.resources.resource.extensions.resource

import com.javiersc.resources.resource.Resource
import com.javiersc.resources.resource.Resource.Error
import com.javiersc.resources.resource.Resource.Loading
import com.javiersc.resources.resource.Resource.Success
import com.javiersc.resources.resource.extensions.asError
import com.javiersc.resources.resource.extensions.asSuccess

/**
 * Combine two Resource into one.
 * Success happens when both are success
 * Loading happens when one is Loading and the another is Loading or Success
 * If there is only an error, [defaultError] will be used
 * If both resources are error, [error] will be used or [defaultError] if it is null
 */
@Suppress("ComplexMethod")
inline fun <reified R, reified R2, reified R3, reified E, reified E2, reified E3> Resource<R, E>.combine(
    resource: Resource<R2, E2>,
    success: (R, R2) -> R3,
    noinline error: ((E, E2) -> E3)? = null,
    defaultError: E3,
): Resource<R3, E3> = when {
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
inline fun <reified R, reified R2, reified R3, reified E> Resource<R, E>.combine(
    resource: Resource<R2, E>,
    success: (R, R2) -> R3,
): Resource<R3, E> = when {
    this is Success && resource is Success -> success(this.data, resource.data).asSuccess()
    this is Loading && resource is Loading -> Loading
    this is Loading || this is Success && resource is Loading || resource is Success -> Loading
    this is Error -> this
    resource is Error -> resource
    else -> throw IllegalStateException("This state should not be possible")
}
