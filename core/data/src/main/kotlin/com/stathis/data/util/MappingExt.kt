package com.stathis.data.util

import com.stathis.model.Result
import com.stathis.network.model.NetworkResult
import com.stathis.util.errors.NetworkError
import java.util.concurrent.TimeoutException

/**
 * Helper fun to simplify the procedure of performing a simple api call
 * & perform basic mapping from a DtoModel to a Domain Model
 */

internal suspend fun <DtoModel, DomainModel> mapToDomainResult(
    networkCall: suspend () -> NetworkResult<DtoModel>,
    mapping: suspend (DtoModel?) -> DomainModel
): Result<DomainModel> = try {
    val result = networkCall.invoke()

    when (result) {
        is NetworkResult.Success -> {
            val mappedResult = mapping.invoke(result.body)
            Result.Success(data = mappedResult)
        }

        is NetworkResult.Error -> {
            Result.Error(
                NetworkError.Generic(
                    errorCode = result.responseCode,
                    message = result.errorBody
                )
            )
        }
    }
} catch (e: Exception) {
    Result.Error(e.toNetworkError())
}

/**
 * Helper fun to map the mapping exception to a [NetworkError].
 */

private fun Exception.toNetworkError() = when (this) {
    is TimeoutException -> NetworkError.Timeout(message = message.toString())
    else -> NetworkError.ConnectionIssue(message = message.toString())
}
