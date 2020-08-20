package com.javiersc.resources.resource.extensions.resource

import com.javiersc.resources.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Extension function to transform a Resource into a Flow.
 */
public inline fun <reified R, reified E> Resource<R, E>.asFlow(): Flow<Resource<R, E>> = flowOf(this)
