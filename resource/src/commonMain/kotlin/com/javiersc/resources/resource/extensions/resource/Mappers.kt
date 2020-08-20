package com.javiersc.resources.resource.extensions.resource

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
public inline fun <reified R, reified R2, reified E, reified E2> Resource<R, E>.map(
    data: (R) -> R2,
    error: (E) -> E2,
): Resource<R2, E2> = when (this) {
    is Resource.Loading -> this
    is Resource.Success -> Resource.Success(data(this.data))
    is Resource.Error -> Resource.Error(error(this.error))
}

/**
 * Map any Resource to another Resource.
 * @param R is the original resource type.
 * @param E is the original error type.
 * @param R2 the new resource type.
 * @param data to be mapped to [R2].
 */
public inline fun <reified R, reified R2, reified E> Resource<R, E>.mapSuccess(data: (R) -> R2): Resource<R2, E> {
    return when (this) {
        is Resource.Loading -> this
        is Resource.Success -> Resource.Success(data(this.data))
        is Resource.Error -> this
    }
}

/**
 * Map any Resource to another Resource.
 * @param R is the original resource type.
 * @param E is the original error type.
 * @param E2 the new error type.
 * @param error to be mapped to [E2].
 */
public inline fun <reified R, reified E, reified E2> Resource<R, E>.mapError(error: (E) -> E2): Resource<R, E2> {
    return when (this) {
        is Resource.Loading -> this
        is Resource.Success -> this
        is Resource.Error -> Resource.Error(error(this.error))
    }
}
