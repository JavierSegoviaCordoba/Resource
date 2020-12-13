package com.javiersc.resource.extensions.resource

import com.javiersc.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Extension function to transform a Resource into a Flow.
 */
public fun <S, E> Resource<S, E>.asFlow(): Flow<Resource<S, E>> = flowOf(this)
