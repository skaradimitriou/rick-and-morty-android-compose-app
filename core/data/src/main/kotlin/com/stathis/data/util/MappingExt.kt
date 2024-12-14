package com.stathis.data.util

import com.stathis.common.errors.NetworkError
import com.stathis.model.Result
import retrofit2.Response
import java.util.concurrent.TimeoutException

/**
 * Helper fun to simplify the procedure of performing a simple api call
 * & perform basic mapping from a DtoModel to a Domain Model
 */

internal suspend fun <DtoModel, DomainModel> mapToDomainResult(
    networkCall: suspend () -> Response<DtoModel>,
    mapping: suspend (DtoModel?) -> DomainModel
): Result<DomainModel> = try {
    val result = networkCall.invoke()

    if (result.isSuccessful && result.body() != null) {
        val mappedResult = mapping.invoke(result.body())
        Result.Success(data = mappedResult)
    } else {
        Result.Error(
            NetworkError.Generic(
                errorCode = result.code(),
                message = result.errorBody()?.string().toString()
            )
        )
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
