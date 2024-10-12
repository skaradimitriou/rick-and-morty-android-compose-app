package com.stathis.domain.usecases.episodes

import com.stathis.common.util.toNotNull
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.domain.usecases.episodes.FetchEpisodeDetailsUseCase.EpisodeDetails
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchEpisodeDetailsUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository,
    private val charactersRepository: CharactersRepository
) : BaseUseCase<Result<EpisodeDetails>> {

    override suspend fun invoke(vararg args: Any?): Flow<Result<EpisodeDetails>> = flow {
        val id = (args.getOrNull(0) as? Int?).toNotNull()
        val model = EpisodeDetails()

        coroutineScope {
            async { episodesRepository.fetchEpisodeInfo(id) }.await().collect { episodeResult ->
                when (episodeResult) {
                    is Result.Loading -> Unit

                    is Result.Success -> {
                        model.episode = episodeResult.data

                        async { charactersRepository.getMultipleCharacterById(episodeResult.data.characters) }
                            .await()
                            .collect { charactersResult ->
                                when (charactersResult) {
                                    is Result.Loading -> Unit

                                    is Result.Success -> {
                                        model.characters = charactersResult.data
                                        emit(Result.Success(data = model))
                                    }

                                    is Result.Error -> {
                                        emit(Result.Error(charactersResult.errorCode, charactersResult.message))
                                    }
                                }
                            }
                    }

                    is Result.Error -> emit(Result.Error(episodeResult.errorCode, episodeResult.message))
                }
            }
        }
    }

    data class EpisodeDetails(
        var episode: Episode? = null,
        var characters: List<CharacterResponse>? = null
    )
}
