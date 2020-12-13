package com.javiersc.resource.extensions.resource

import com.javiersc.resource.Resource

/**
 * Map any Resource to another Resource.
 * @param S is the original resource type.
 * @param E is the original error type.
 * @param NS the new resource type.
 * @param NE the new error type.
 * @param success to be mapped to [NS].
 * @param error to be mapped to [NE].
 */
public fun <S, NS, E, NE> Resource<S, E>.map(success: (S) -> NS, error: (E) -> NE): Resource<NS, NE> = when (this) {
    is Resource.Loading -> this
    is Resource.Success -> Resource.Success(success(this.data))
    is Resource.Error -> Resource.Error(error(this.error))
}

/**
 * Map any Resource to another object, for example a ScreenState sealed class.
 * @param S is the original resource type.
 * @param E is the original error type.
 * @param T the new object type.
 * @param success to be mapped to [T].
 * @param error to be mapped to [T].
 */
public fun <S, E, T> Resource<S, E>.map(loading: () -> T, success: (S) -> T, error: (E) -> T): T = when (this) {
    is Resource.Loading -> loading()
    is Resource.Success -> success(this.data)
    is Resource.Error -> error(this.error)
}

/**
 * Map any Resource to another Resource.
 * @param S is the original resource type.
 * @param E is the original error type.
 * @param NS the new resource type.
 * @param success to be mapped to [NS].
 */
public fun <S, E, NS> Resource<S, E>.mapSuccess(success: (S) -> NS): Resource<NS, E> {
    return when (this) {
        is Resource.Loading -> this
        is Resource.Success -> Resource.Success(success(this.data))
        is Resource.Error -> this
    }
}

/**
 * Map any Resource to another Resource.
 * @param S is the original resource type.
 * @param E is the original error type.
 * @param NE the new error type.
 * @param error to be mapped to [NE].
 */
public fun <S, E, NE> Resource<S, E>.mapError(error: (E) -> NE): Resource<S, NE> {
    return when (this) {
        is Resource.Loading -> this
        is Resource.Success -> this
        is Resource.Error -> Resource.Error(error(this.error))
    }
}
