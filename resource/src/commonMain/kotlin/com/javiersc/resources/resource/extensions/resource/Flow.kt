package com.javiersc.resources.resource.extensions.resource

import com.javiersc.resources.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Extension function to transform a Resource into a Flow.
 */
inline fun <reified R, reified E> Resource<R, E>.asFlow(): Flow<Resource<R, E>> = flow { emit(this@asFlow) }
