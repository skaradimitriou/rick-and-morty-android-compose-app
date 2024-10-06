package com.stathis.domain.usecases.characters

import com.stathis.common.util.toNotNull
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchCharacterDetailsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val episodesRepository: EpisodesRepository
) : BaseUseCase<FetchCharacterDetailsUseCase.CharacterDetails> {

    override suspend fun invoke(vararg args: Any?): Flow<CharacterDetails> = flow {
        coroutineScope {
            val characterId = (args.getOrNull(0) as? Int?).toNotNull()

            val dataModel = CharacterDetails()

            val character = async { charactersRepository.getCharacterById(characterId) }.await().firstOrNull()

            val episodes = async {
                episodesRepository.fetchMultipleEpisodeInfo(character?.episode.toNotNull())
            }.await().firstOrNull()

            dataModel.apply {
                this.character = character
                this.episodes = episodes
            }

            emit(dataModel)
        }
    }

    data class CharacterDetails(
        var character: CharacterResponse? = null,
        var episodes: List<Episode>? = null
    )
}
