package com.stathis.data.repository

import com.stathis.data.mapper.episodes.EpisodesMapper
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.episodes.Episode
import com.stathis.network.service.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class EpisodesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RickAndMortyApi
) : EpisodesRepository {

    override suspend fun fetchEpisodeInfo(id: Int): Flow<Episode> = flow {
        val result = remoteDataSource.getEpisodeById(id)?.body()
        val mappedResult = EpisodesMapper.toDomainModel(result)
        emit(mappedResult)
    }

    override suspend fun fetchMultipleEpisodeInfo(ids: List<String>): Flow<List<Episode>> = flow {
        val result = remoteDataSource.getMultipleEpisodesById(ids)?.body()?.map {
            EpisodesMapper.toDomainModel(it)
        } ?: listOf()
        emit(result)
    }
}
