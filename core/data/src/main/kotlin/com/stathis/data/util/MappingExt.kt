package com.stathis.data.util

import com.stathis.model.Result
import retrofit2.Response

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
            errorCode = result.code(),
            message = result.errorBody().toString()
        )
    }
} catch (e: Exception) {
    Result.Error(
        errorCode = 404,
        message = e.message.toString()
    )
}
