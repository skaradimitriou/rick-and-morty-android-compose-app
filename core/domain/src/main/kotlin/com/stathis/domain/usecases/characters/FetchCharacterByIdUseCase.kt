package com.stathis.domain.usecases.characters

import com.stathis.common.util.toNotNull
import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) : BaseUseCase<Result<CharacterResponse>> {

    override suspend fun invoke(vararg args: Any?): Flow<Result<CharacterResponse>> {
        val characterId = (args.getOrNull(0) as? Int?).toNotNull()
        return repository.getCharacterById(characterId)
    }
}
