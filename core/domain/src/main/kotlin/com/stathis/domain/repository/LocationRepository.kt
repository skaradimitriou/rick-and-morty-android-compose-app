package com.stathis.domain.repository

import com.stathis.model.Result
import com.stathis.model.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getLocationById(id: Int): Flow<Result<Location>>

    suspend fun getMultipleLocationsById(ids: List<String>): Flow<Result<List<Location>>>

    suspend fun getLocationByName(name: String): Flow<Result<List<Location>>>
}
