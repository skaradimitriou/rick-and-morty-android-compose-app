package com.stathis.domain.usecases.search

import com.stathis.common.util.toNotNull
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.domain.usecases.search.FetchQueryResultsUseCase.QueryResult
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchQueryResultsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val episodesRepository: EpisodesRepository
) : BaseUseCase<QueryResult> {

    override suspend fun invoke(vararg args: Any?): Flow<QueryResult> = flow {
        val query = (args.getOrNull(0) as? String?).toNotNull()

        coroutineScope {
            val characterCall = async { charactersRepository.getCharacterByName(query) }.await()
            val episodesCall = async { episodesRepository.fetchEpisodesByName(query) }.await()

            characterCall.combine(episodesCall) { charactersResult, episodesResult ->
                when {
                    charactersResult is Result.Success && episodesResult is Result.Success -> {
                        QueryResult.Success(
                            characters = charactersResult.data,
                            episodes = episodesResult.data
                        )
                    }

                    charactersResult is Result.Error || episodesResult is Result.Error -> {
                        QueryResult.Error
                    }

                    else -> QueryResult.None
                }
            }.collect { result ->
                emit(result)
            }
        }
    }

    sealed class QueryResult {
        data object None : QueryResult()
        data class Success(
            val characters: List<CharacterResponse>,
            val episodes: List<Episode>
        ) : QueryResult()

        data object Error : QueryResult()
    }
}
