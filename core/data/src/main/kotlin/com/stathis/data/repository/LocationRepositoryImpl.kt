package com.stathis.data.repository

import com.stathis.data.mapper.location.LocationMapper
import com.stathis.data.mapper.location.LocationWrapperMapper
import com.stathis.data.util.mapToDomainResult
import com.stathis.domain.repository.LocationRepository
import com.stathis.model.Result
import com.stathis.model.location.Location
import com.stathis.network.datasource.LocationsRemoteDataSource
import com.stathis.util.util.toListOf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class LocationRepositoryImpl(
    private val remoteDataSource: LocationsRemoteDataSource
) : LocationRepository {

    override suspend fun getLocationById(id: Int): Flow<Result<Location>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.fetchLocationById(id) },
            mapping = { LocationMapper.toDomainModel(it) }
        )

        emit(result)
    }

    override suspend fun getMultipleLocationsById(ids: List<String>): Flow<Result<List<Location>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.fetchMultipleLocationsById(ids) },
            mapping = { it.toListOf { LocationMapper.toDomainModel(it) } }
        )

        emit(result)
    }

    override suspend fun getLocationByName(name: String): Flow<Result<List<Location>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.fetchLocationByName(name) },
            mapping = { LocationWrapperMapper.toDomainModel(it) }
        )

        emit(result)
    }
}
