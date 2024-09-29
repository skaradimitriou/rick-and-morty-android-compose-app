package com.stathis.domain.usecases.characters

import com.stathis.data.repository.CharactersRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.model.characters.CharacterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) : BaseUseCase<CharacterResponse> {

    override suspend fun invoke(vararg args: Any?): Flow<CharacterResponse> {
        val characterId = args.getOrNull(0) as? Int ?: 0
        return repository.getCharacterById(characterId)
    }
}