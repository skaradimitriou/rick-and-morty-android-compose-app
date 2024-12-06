package com.stathis.domain.usecases.characters

import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse

class FetchAllCharactersUseCase(
    private val repository: CharactersRepository
) : BaseUseCase<Result<List<CharacterResponse>>> {

    override suspend fun invoke(vararg args: Any?) = repository.getAllCharacters()
}
