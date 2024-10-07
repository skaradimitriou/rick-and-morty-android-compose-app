package com.stathis.data.util

import com.stathis.model.Result
import retrofit2.Response

/**
 * Helper fun to simplify the procedure of performing a simple api call
 * & perform basic mapping from a DtoModel to a Domain Model
 */

suspend fun <DtoModel, DomainModel> mapToDomainResult(
    networkCall: suspend () -> Response<DtoModel>,
    mapping: suspend (DtoModel?) -> DomainModel
): Result<DomainModel> {
    val result = networkCall.invoke()

    return try {
        if (result.isSuccessful && result.body() != null) {
            val mappedResult = mapping.invoke(result.body())
            Result.Success(data = mappedResult)
        } else {
            Result.Error(
                errorCode = result.code(),
                message = result.message().toString()
            )
        }
    } catch (e: Exception) {
        Result.Error(
            errorCode = result.code(),
            message = e.message.toString()
        )
    }
}
