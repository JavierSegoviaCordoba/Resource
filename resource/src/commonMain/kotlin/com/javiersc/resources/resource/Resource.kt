package com.javiersc.resources.resource

import kotlinx.serialization.Serializable
import kotlinx.serialization.ContextualSerialization as CS

/**
 * This class lets wrap any thing and add an associated state:
 * Loading, Success, Error and Cache
 * @param R is the type of the Resource
 * @Param E is the type of the Error
 */
@Serializable
sealed class Resource<out R, out E> {

    /**
     * Loading state which has no params
     */
    @Serializable
    object Loading : Resource<@CS Nothing, @CS Nothing>()

    /**
     * Success state which has a param [data] of type [R]. Can be null.
     * @param data can be any thing to be wrapped.
     */
    @Serializable
    data class Success<out R>(val data: R?) : Resource<R, @CS Nothing>()

    /**
     * Cache state which has a param [error] of type [E]. Can be null.
     * @param error can be any error to be wrapped.
     */
    @Serializable
    data class Error<out E>(val error: E?) : Resource<@CS Nothing, E>()

    /**
     * Cache state which has a param [data] of type [R]. Can be null.
     * @param data can be any thing to be wrapped.
     */
    @Serializable
    data class Cache<out R>(val data: R?) : Resource<R, @CS Nothing>()

    val isLoading: Boolean get() = this is Loading
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    val isCache: Boolean get() = this is Cache

    /**
     * A builder which lets fold a Resource with a series of functions that will be invoked based on
     * the Resource state.
     * @param resource to be folded.
     */
    @Suppress("TooManyFunctions")
    inner class Folder(private val resource: Resource<R, E>) {

        /**
         * @param function callback will be invoked if Resource is Loading.
         * Ideal to show a progress bar. Example:
         * resource.fold {
         *     loading { progressBar.show() }
         * }
         */
        fun loading(function: () -> Unit) {
            if (resource is Loading) function()
        }

        /**
         * @param function callback will be invoked if Resource is not Loading
         * Here is easy to hide the progress bar because it is invoked if resource pass from Loading
         * to Success, Error or Cache state
         */
        fun noLoading(function: () -> Unit) {
            if (resource !is Loading) function()
        }

        /**
         * @param function callback will be invoked if Resource is Success and has data
         * Ideal to populate the success data
         */
        fun success(function: (R) -> Unit) {
            if (resource is Success && resource.data != null) function(resource.data)
        }

        /**
         * @param function callback will be invoked if Resource is Success and has no data
         * Perfect to show a progress indicator
         */
        fun successEmpty(function: () -> Unit) {
            if (resource is Success && resource.data == null) function()
        }

        /**
         * @param function callback will be invoked if Resource is not Success
         */
        fun noSuccess(function: () -> Unit) {
            if (resource !is Success) function()
        }

        /**
         * @param function callback will be invoked if Resource is Error
         * Ideal to show an error message when something has failed
         */
        fun error(function: (E) -> Unit) {
            if (resource is Error && resource.error != null) function(resource.error)
        }

        /**
         * @param function callback will be invoked if Resource is Error and has no error data
         */
        fun errorEmpty(function: () -> Unit) {
            if (resource is Error && resource.error == null) function()
        }

        /**
         * @param function callback will be invoked if Resource is not Error
         */
        fun noError(function: () -> Unit) {
            if (resource !is Error) function()
        }

        /**
         * @param function callback will be invoked if Resource is Cache and has data
         */
        fun cache(function: (R) -> Unit) {
            if (resource is Cache && resource.data != null) function(resource.data)
        }

        /**
         * @param function callback will be invoked if Resource is Cache and has no data
         */
        fun cacheEmpty(function: () -> Unit) {
            if (resource is Cache && resource.data == null) function()
        }

        /**
         * @param function callback will be invoked if Resource is not Cache
         */
        fun noCache(function: () -> Unit) {
            if (resource !is Cache) function()
        }
    }
}
