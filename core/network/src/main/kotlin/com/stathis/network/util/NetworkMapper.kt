package com.stathis.network.util

import com.stathis.network.model.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess

/**
 * Helper method to simplify making a network call and returning a [NetworkResult] with Ktor HttpClient.
 */
internal suspend inline fun <reified T> HttpClient.mapApiCallToNetworkResult(url: String): NetworkResult<T?> {
    val result = try {
        val call = get(url).call.response

        if (call.status.isSuccess()) {
            NetworkResult.Success<T?>(body = call.body())
        } else {
            NetworkResult.Error(
                responseCode = call.status.value,
                errorBody = call.status.description
            )
        }
    } catch (e: Exception) {
        NetworkResult.Error(
            responseCode = 500,
            errorBody = e.toString()
        )
    }

    return result
}
