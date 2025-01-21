package com.stathis.domain.usecases.episodes

import com.stathis.domain.consts.UNSUPPORTED_ID
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.usecases.episodes.FetchEpisodeDetailsUseCase.EpisodeDetails
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class FetchEpisodeDetailsUseCase(
    private val episodesRepository: EpisodesRepository,
    private val charactersRepository: CharactersRepository
) {

    operator fun invoke(episodeId: Int): Flow<Result<EpisodeDetails>> = flow {
        if (episodeId == UNSUPPORTED_ID) {
            error("Episode id with zero value provided")
        }

        val model = EpisodeDetails()

        episodesRepository.fetchEpisodeInfo(episodeId).collect { episodeResult ->
            when (episodeResult) {
                is Result.Loading -> Unit

                is Result.Success -> {
                    model.episode = episodeResult.data

                    val charactersResult = charactersRepository.getMultipleCharacterById(
                        ids = episodeResult.data.characters
                    ).firstOrNull()

                    if (charactersResult is Result.Success) {
                        model.characters = charactersResult.data
                    }

                    emit(Result.Success(model))
                }

                is Result.Error -> emit(Result.Error(episodeResult.exception))
            }
        }
    }

    data class EpisodeDetails(
        var episode: Episode? = null,
        var characters: List<CharacterResponse>? = null
    )
}
