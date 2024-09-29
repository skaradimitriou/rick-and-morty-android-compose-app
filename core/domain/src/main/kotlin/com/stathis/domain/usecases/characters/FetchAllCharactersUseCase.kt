package com.stathis.domain.usecases.characters

import com.stathis.data.repository.CharactersRepository
import com.stathis.domain.usecases.BaseUseCase
import com.stathis.model.characters.CharacterResponse
import javax.inject.Inject

class FetchAllCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) : BaseUseCase<List<CharacterResponse>> {

    override suspend fun invoke(vararg args: Any?) = repository.getAllCharacters()
}