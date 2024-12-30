package com.stathis.domain.usecases.characters

import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.repository.LocationRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.domain.usecases.characters.FetchCharacterDetailsUseCase.CharacterDetails
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import com.stathis.model.location.Location
import com.stathis.util.util.toNotNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class FetchCharacterDetailsUseCase(
    private val charactersRepository: CharactersRepository,
    private val locationRepository: LocationRepository,
    private val episodesRepository: EpisodesRepository
) : BaseUseCase<Result<CharacterDetails>> {

    override suspend fun invoke(vararg args: Any?): Flow<Result<CharacterDetails>> = flow {
        val characterId = (args.getOrNull(0) as? Int?).toNotNull()
        charactersRepository.getCharacterById(characterId).collect { characterResult ->
            when (characterResult) {
                is Result.Loading -> Unit

                is Result.Success -> {
                    combine(
                        locationRepository.getLocationById(id = characterResult.data.origin.id),
                        locationRepository.getLocationById(id = characterResult.data.location.id),
                        episodesRepository.fetchMultipleEpisodeInfo(characterResult.data.episode.toNotNull())
                    ) { originLocationResult, locationResult, episodeResult ->
                        val results = listOf(originLocationResult, locationResult, episodeResult)
                        when {
                            results.all { it is Result.Success } -> {
                                val dataModel = CharacterDetails(
                                    character = characterResult.data,
                                    originInfo = (originLocationResult as Result.Success).data,
                                    locationInfo = (locationResult as Result.Success).data,
                                    episodes = (episodeResult as Result.Success).data
                                )
                                Result.Success(dataModel)
                            }

                            results.any { it is Result.Error } -> {
                                Result.Error<CharacterDetails>(Exception())
                            }

                            else -> Result.Loading()
                        }
                    }.collect { result -> emit(result) }
                }

                is Result.Error -> emit(Result.Error(characterResult.exception))
            }
        }
    }

    data class CharacterDetails(
        val character: CharacterResponse,
        val originInfo: Location,
        val locationInfo: Location,
        val episodes: List<Episode>
    )
}
