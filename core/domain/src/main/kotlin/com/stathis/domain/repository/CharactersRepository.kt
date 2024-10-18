package com.stathis.domain.repository

import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getAllCharacters(): Flow<Result<List<CharacterResponse>>>

    suspend fun getCharacterById(id: Int): Flow<Result<CharacterResponse>>

    suspend fun getCharacterByName(name: String): Flow<Result<List<CharacterResponse>>>

    suspend fun getMultipleCharacterById(ids: List<String>): Flow<Result<List<CharacterResponse>>>
}
