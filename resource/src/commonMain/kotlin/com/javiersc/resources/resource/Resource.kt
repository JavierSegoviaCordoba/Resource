package com.javiersc.resources.resource

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * This class lets wrap any thing and add an associated state:
 * Loading, Success and Error.
 * @param R is the type of the Resource
 * @Param E is the type of the Error
 */
@Serializable
sealed class Resource<out R, out E> {

    /**
     * Loading state which has no params
     */
    @Serializable
    object Loading : Resource<@Contextual Nothing, @Contextual Nothing>()

    /**
     * Success state which has a param [data] of type [R].
     * @param data can be any object to be wrapped.
     */
    @Serializable
    data class Success<out R>(val data: R) : Resource<R, @Contextual Nothing>()

    /**
     * Error state which has a param [error] of type [E]
     * @param error can be any object to be wrapped.
     */
    @Serializable
    data class Error<out E>(val error: E) : Resource<@Contextual Nothing, E>()

    val isLoading: Boolean get() = this is Loading
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error

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
         * to Success, Error
         */
        fun noLoading(function: () -> Unit) {
            if (resource !is Loading) function()
        }

        /**
         * @param function callback will be invoked if Resource is Success and has data
         * Ideal to populate the success data
         */
        fun success(function: (R) -> Unit) {
            if (resource is Success) function(resource.data)
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
            if (resource is Error) function(resource.error)
        }

        /**
         * @param function callback will be invoked if Resource is not Error
         */
        fun noError(function: () -> Unit) {
            if (resource !is Error) function()
        }
    }
}
