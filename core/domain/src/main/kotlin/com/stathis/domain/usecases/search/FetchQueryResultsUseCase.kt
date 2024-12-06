package com.stathis.domain.usecases.search

import com.stathis.common.util.toNotNull
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.repository.LocationRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.domain.usecases.search.FetchQueryResultsUseCase.QueryResult
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import com.stathis.model.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class FetchQueryResultsUseCase(
    private val charactersRepository: CharactersRepository,
    private val episodesRepository: EpisodesRepository,
    private val locationRepository: LocationRepository
) : BaseUseCase<QueryResult> {

    override suspend fun invoke(vararg args: Any?): Flow<QueryResult> = flow {
        val query = (args.getOrNull(0) as? String?).toNotNull()

        combine(
            charactersRepository.getCharacterByName(query),
            episodesRepository.fetchEpisodesByName(query),
            locationRepository.getLocationByName(query)
        ) { charactersResult, episodesResult, locationsResult ->
            val results = listOf(charactersResult, episodesResult, locationsResult)
            when {
                results.any { it is Result.Success } -> {
                    QueryResult.Success(
                        characters = (charactersResult as? Result.Success)?.data ?: listOf(),
                        episodes = (episodesResult as? Result.Success)?.data ?: listOf(),
                        locations = (locationsResult as? Result.Success?)?.data ?: listOf(),
                    )
                }

                results.any { it is Result.Error } -> {
                    QueryResult.Error
                }

                else -> QueryResult.None
            }
        }.collect { result -> emit(result) }
    }

    sealed class QueryResult {
        data object None : QueryResult()
        data class Success(
            val characters: List<CharacterResponse>,
            val episodes: List<Episode>,
            val locations: List<Location>
        ) : QueryResult()

        data object Error : QueryResult()
    }
}
