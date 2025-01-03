package com.stathis.network.model

/**
 * Sealed class to represent the different states of a Network call.
 */

sealed class NetworkResult<T> {

    /**
     * [NetworkResult.Success] represents a successful network call.
     * @param body: The data that the network call responds with.
     */

    data class Success<T>(val body: T) : NetworkResult<T>()

    /**
     * [NetworkResult.Error] represents a failed network call.
     * @param responseCode: The network call's response code
     * @param errorBody: The network call's error body.
     */

    data class Error<T>(
        val responseCode: Int,
        val errorBody: String
    ) : NetworkResult<T>()
}
