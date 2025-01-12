package com.stathis.network.datasource

import com.stathis.network.model.NetworkResult
import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto

interface CharactersRemoteDataSource {

    /**
     * Fetches all characters.
     */
    suspend fun fetchAllCharacters(): NetworkResult<CharacterWrapperDto?>

    /**
     * Fetches single character by its id.
     * @param id: the id of the character
     */
    suspend fun fetchCharacterById(id: Int): NetworkResult<CharacterResponseDto?>

    /**
     * Fetches multiple characters by its id.
     * @param ids: a list of character ids to be fetched from remote source.
     */
    suspend fun fetchMultipleCharactersById(ids: List<String>): NetworkResult<List<CharacterResponseDto>?>

    /**
     * Fetches single character by its name.
     * @param name: the character name.
     */
    suspend fun fetchCharacterByName(name: String): NetworkResult<CharacterWrapperDto?>
}
