package com.javiersc.resources.resource.extensions.resource

import com.javiersc.resources.resource.Resource

/**
 * Map any Resource to another Resource.
 * @param R is the original resource type.
 * @param E is the original error type.
 * @param R2 the new resource type.
 * @param E2 the new error type.
 * @param success to be mapped to [R2].
 * @param error to be mapped to [E2].
 */
public fun <R, R2, E, E2> Resource<R, E>.map(success: (R) -> R2, error: (E) -> E2): Resource<R2, E2> = when (this) {
    is Resource.Loading -> this
    is Resource.Success -> Resource.Success(success(this.data))
    is Resource.Error -> Resource.Error(error(this.error))
}

/**
 * Map any Resource to another object, for example a ScreenState sealed class.
 * @param R is the original resource type.
 * @param E is the original error type.
 * @param T the new object type.
 * @param success to be mapped to [T].
 * @param error to be mapped to [T].
 */
public fun <R, E, T> Resource<R, E>.map(loading: () -> T, success: (R) -> T, error: (E) -> T): T = when (this) {
    is Resource.Loading -> loading()
    is Resource.Success -> success(this.data)
    is Resource.Error -> error(this.error)
}

/**
 * Map any Resource to another Resource.
 * @param R is the original resource type.
 * @param E is the original error type.
 * @param R2 the new resource type.
 * @param success to be mapped to [R2].
 */
public fun <R, R2, E> Resource<R, E>.mapSuccess(success: (R) -> R2): Resource<R2, E> {
    return when (this) {
        is Resource.Loading -> this
        is Resource.Success -> Resource.Success(success(this.data))
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
public fun <R, E, E2> Resource<R, E>.mapError(error: (E) -> E2): Resource<R, E2> {
    return when (this) {
        is Resource.Loading -> this
        is Resource.Success -> this
        is Resource.Error -> Resource.Error(error(this.error))
    }
}
