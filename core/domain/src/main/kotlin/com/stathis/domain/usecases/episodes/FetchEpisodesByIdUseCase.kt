package com.stathis.domain.usecases.episodes

import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.flow.Flow

class FetchEpisodesByIdUseCase(private val repository: EpisodesRepository) {

    suspend fun invoke(episodeIds: List<String>): Flow<Result<List<Episode>>> = if (episodeIds.isEmpty()) {
        error("Empty episode ids provided.")
    } else {
        repository.fetchMultipleEpisodeInfo(episodeIds)
    }
}
