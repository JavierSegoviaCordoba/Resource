package com.javiersc.resource

import kotlinx.serialization.Serializable
import kotlinx.serialization.ContextualSerialization as CS

@Serializable
sealed class Resource<out R, out E> {
    @Serializable
    data class Loading<out R>(val resource: R? = null) : Resource<R, @CS Nothing>()

    @Serializable
    data class Cache<out R>(val resource: R?) : Resource<R, @CS Nothing>()

    @Serializable
    data class Success<out R>(val resource: R) : Resource<R, @CS Nothing>() {
        @Serializable
        object NoData : Resource<@CS Nothing, @CS Nothing>() {
            override fun toString(): String = "Success resource but it has no data"
        }
    }

    @Serializable
    data class Error<out E>(val error: E?) : Resource<@CS Nothing, E>()
}