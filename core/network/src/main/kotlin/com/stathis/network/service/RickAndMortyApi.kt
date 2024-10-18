package com.stathis.network.service

import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto
import com.stathis.network.model.episodes.EpisodeDto
import com.stathis.network.model.episodes.EpisodeWrapperDto
import com.stathis.network.util.CHARACTERS_BY_ID_ENDPOINT
import com.stathis.network.util.CHARACTER_BY_ID_ENDPOINT
import com.stathis.network.util.CHARACTER_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    /**
     * Endpoints for Characters
     */

    @GET(CHARACTER_ENDPOINT)
    suspend fun getAllCharacters(): Response<CharacterWrapperDto?>

    @GET(CHARACTER_BY_ID_ENDPOINT)
    suspend fun getCharacterById(
        @Path(value = "id") id: Int
    ): Response<CharacterResponseDto?>

    @GET(CHARACTERS_BY_ID_ENDPOINT)
    suspend fun getMultipleCharactersById(
        @Path(value = "id") ids: List<String>
    ): Response<List<CharacterResponseDto>?>

    @GET(CHARACTER_ENDPOINT)
    suspend fun getCharacterByName(
        @Query(value = "name") name: String
    ): Response<CharacterWrapperDto?>

    /**
     * Endpoints for Episodes
     */

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path(value = "id") id: Int
    ): Response<EpisodeDto?>

    @GET("episode")
    suspend fun getEpisodesByName(
        @Query(value = "name") name: String
    ): Response<EpisodeWrapperDto?>

    @GET("episode/{id}")
    suspend fun getMultipleEpisodesById(
        @Path(value = "id") ids: List<String>
    ): Response<List<EpisodeDto?>>
}
