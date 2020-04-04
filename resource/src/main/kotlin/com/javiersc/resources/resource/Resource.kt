package com.javiersc.resources.resource

import kotlinx.serialization.Serializable
import kotlinx.serialization.ContextualSerialization as CS

@Serializable
sealed class Resource<out R, out E> {

    @Serializable
    object Loading : Resource<@CS Nothing, @CS Nothing>()

    @Serializable
    data class Success<out R>(val data: R?) : Resource<R, @CS Nothing>()

    @Serializable
    data class Error<out E>(val error: E?) : Resource<@CS Nothing, E>()

    @Serializable
    data class Cache<out R>(val data: R?) : Resource<R, @CS Nothing>()

    val isLoading: Boolean get() = this is Loading
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    val isCache: Boolean get() = this is Cache

    @Suppress("TooManyFunctions")
    inner class Folder(val resource: Resource<R, E>) {

        fun loading(function: () -> Unit) {
            if (resource is Loading) function()
        }

        fun noLoading(function: () -> Unit) {
            if (resource !is Loading) function()
        }

        fun success(function: (R) -> Unit) {
            if (resource is Success && resource.data != null) function(resource.data)
        }

        fun successEmpty(function: () -> Unit) {
            if (resource is Success && resource.data == null) function()
        }

        fun noSuccess(function: () -> Unit) {
            if (resource !is Success) function()
        }

        fun error(function: (E) -> Unit) {
            if (resource is Error && resource.error != null) function(resource.error)
        }

        fun errorEmpty(function: () -> Unit) {
            if (resource is Error && resource.error == null) function()
        }

        fun noError(function: () -> Unit) {
            if (resource !is Error) function()
        }

        fun cache(function: (R) -> Unit) {
            if (resource is Cache && resource.data != null) function(resource.data)
        }

        fun cacheEmpty(function: () -> Unit) {
            if (resource is Cache && resource.data == null) function()
        }

        fun noCache(function: () -> Unit) {
            if (resource !is Cache) function()
        }
    }
}
