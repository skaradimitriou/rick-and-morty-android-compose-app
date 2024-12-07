package com.stathis.data.repository

import com.stathis.common.util.toListOf
import com.stathis.data.mapper.location.LocationMapper
import com.stathis.data.mapper.location.LocationWrapperMapper
import com.stathis.data.util.mapToDomainResult
import com.stathis.domain.repository.LocationRepository
import com.stathis.model.Result
import com.stathis.model.location.Location
import com.stathis.network.service.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class LocationRepositoryImpl(
    private val remoteDataSource: RickAndMortyApi
) : LocationRepository {

    override suspend fun getLocationById(id: Int): Flow<Result<Location>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.getLocationById(id) },
            mapping = { LocationMapper.toDomainModel(it) }
        )

        emit(result)
    }

    override suspend fun getMultipleLocationsById(ids: List<String>): Flow<Result<List<Location>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.getMultipleLocationsById(ids) },
            mapping = { it.toListOf { LocationMapper.toDomainModel(it) } }
        )

        emit(result)
    }

    override suspend fun getLocationByName(name: String): Flow<Result<List<Location>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.getLocationByName(name) },
            mapping = { LocationWrapperMapper.toDomainModel(it) }
        )

        emit(result)
    }
}
