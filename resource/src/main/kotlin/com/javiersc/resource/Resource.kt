package com.javiersc.resource

sealed class Resource<out R, out E> {
    data class Loading<out R>(val resource: R? = null) : Resource<R, Nothing>()
    data class Cache<out R>(val resource: R?) : Resource<R, Nothing>()
    data class Success<out R>(val resource: R) : Resource<R, Nothing>() {
        object NoData : Resource<Nothing, Nothing>() {
            override fun toString(): String = "Success resource but it has no data"
        }
    }

    data class Error<out E>(val error: E?) : Resource<Nothing, E>()
}