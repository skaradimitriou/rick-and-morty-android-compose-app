package com.stathis.data.repository

import com.stathis.model.characters.CharacterResponse
import kotlinx.coroutines.flow.Flow


interface CharactersRepository {

    suspend fun getAllCharacters(): Flow<List<CharacterResponse>>

    suspend fun getCharacterById(id: Int): Flow<CharacterResponse>
}