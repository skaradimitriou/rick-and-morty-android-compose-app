package com.stathis.domain.usecases.search

import com.stathis.common.util.toNotNull
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.domain.usecases.search.FetchQueryResultsUseCase.QueryResults
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchQueryResultsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val episodesRepository: EpisodesRepository
) : BaseUseCase<Result<QueryResults>> {

    override suspend fun invoke(vararg args: Any?): Flow<Result<QueryResults>> = flow {
        val query = (args.getOrNull(0) as? String?).toNotNull()
        var model = QueryResults()

        charactersRepository.getCharacterByName(query).collect { charactersResult ->
            when (charactersResult) {
                is Result.Loading -> Unit

                is Result.Success -> {
                    model.characters = charactersResult.data
                }

                is Result.Error -> emit(Result.Error(charactersResult.errorCode, charactersResult.message))
            }
        }

        episodesRepository.fetchEpisodesByName(query).collect { episodesResult ->
            when (episodesResult) {
                is Result.Loading -> Unit

                is Result.Success -> {
                    model.episodes = episodesResult.data
                }

                is Result.Error -> emit(Result.Error(episodesResult.errorCode, episodesResult.message))
            }
        }

        emit(Result.Success(model))
    }

    data class QueryResults(
        var characters: List<CharacterResponse>? = null,
        var episodes: List<Episode>? = null
    )
}
