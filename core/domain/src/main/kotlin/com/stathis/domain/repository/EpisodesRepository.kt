package com.stathis.domain.repository

import com.stathis.model.Result
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    suspend fun fetchEpisodeInfo(id: Int): Flow<Result<Episode>>

    suspend fun fetchEpisodesByName(name: String): Flow<Result<List<Episode>>>

    suspend fun fetchMultipleEpisodeInfo(ids: List<String>): Flow<Result<List<Episode>>>
}
