package com.stathis.model

sealed class Result<T> {
    data class Loading<T>(val isLoading: Boolean = true) : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
}
