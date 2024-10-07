package com.stathis.domain.repository

import com.stathis.model.Result
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    suspend fun fetchEpisodeInfo(id: Int): Flow<Result<Episode>>

    suspend fun fetchMultipleEpisodeInfo(ids: List<String>): Flow<List<Episode>>
}
