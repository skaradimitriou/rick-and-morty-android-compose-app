package com.stathis.domain.usecases.episodes

import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.model.Result
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.flow.Flow

class FetchEpisodesByIdUseCase(
    private val repository: EpisodesRepository
) : BaseUseCase<Result<List<Episode>>> {

    override suspend fun invoke(vararg args: Any?): Flow<Result<List<Episode>>> {
        val ids = args.getOrNull(0) as? List<String>? ?: listOf()
        return repository.fetchMultipleEpisodeInfo(ids)
    }
}
