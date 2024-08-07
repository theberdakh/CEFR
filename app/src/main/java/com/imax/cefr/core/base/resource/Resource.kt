package com.imax.cefr.core.base.resource

sealed class Resource<out T>(val data: T? = null, val error: Throwable? = null) {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val value: T) : Resource<T>(value)
    data class Error<out T>(val throwable: Throwable) : Resource<T>(error = throwable)
}
