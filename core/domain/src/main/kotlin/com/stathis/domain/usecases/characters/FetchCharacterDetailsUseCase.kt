package com.stathis.domain.usecases.characters

import com.stathis.common.util.toNotNull
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.domain.usecases.characters.FetchCharacterDetailsUseCase.CharacterDetails
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchCharacterDetailsUseCase(
    private val charactersRepository: CharactersRepository,
    private val episodesRepository: EpisodesRepository
) : BaseUseCase<Result<CharacterDetails>> {

    override suspend fun invoke(vararg args: Any?): Flow<Result<CharacterDetails>> = flow {
        val characterId = (args.getOrNull(0) as? Int?).toNotNull()
        val dataModel = CharacterDetails()

        charactersRepository.getCharacterById(characterId).collect { characterResult ->
            when (characterResult) {
                is Result.Loading -> Unit

                is Result.Success -> {
                    dataModel.character = characterResult.data

                    episodesRepository.fetchMultipleEpisodeInfo(
                        characterResult.data.episode.toNotNull()
                    ).collect { episodeResult ->
                        when (episodeResult) {
                            is Result.Loading -> Unit

                            is Result.Success -> {
                                dataModel.episodes = episodeResult.data
                                emit(Result.Success(dataModel))
                            }

                            is Result.Error -> emit(Result.Error(episodeResult.exception))
                        }
                    }
                }

                is Result.Error -> emit(Result.Error(characterResult.exception))
            }
        }
    }

    data class CharacterDetails(
        var character: CharacterResponse? = null,
        var episodes: List<Episode>? = null
    )
}
