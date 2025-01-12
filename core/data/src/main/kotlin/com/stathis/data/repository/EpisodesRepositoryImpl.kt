package com.stathis.data.repository

import com.stathis.data.mapper.episodes.EpisodeListMapper
import com.stathis.data.mapper.episodes.EpisodesMapper
import com.stathis.data.mapper.episodes.EpisodesWrapperMapper
import com.stathis.data.util.mapToDomainResult
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.model.episodes.Episode
import com.stathis.network.datasource.EpisodesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class EpisodesRepositoryImpl(
    private val remoteDataSource: EpisodesRemoteDataSource
) : EpisodesRepository {

    override suspend fun fetchEpisodeInfo(id: Int): Flow<Result<Episode>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.fetchEpisodeById(id) },
            mapping = { EpisodesMapper.toDomainModel(it) }
        )

        emit(result)
    }

    override suspend fun fetchEpisodesByName(name: String): Flow<Result<List<Episode>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.fetchEpisodeByName(name) },
            mapping = { EpisodesWrapperMapper.toDomainModel(it) }
        )

        emit(result)
    }

    override suspend fun fetchMultipleEpisodeInfo(ids: List<String>): Flow<Result<List<Episode>>> = flow {
        val result = mapToDomainResult(
            networkCall = { remoteDataSource.fetchMultipleEpisodesById(ids) },
            mapping = { EpisodeListMapper.toDomainModel(it) }
        )

        emit(result)
    }
}
