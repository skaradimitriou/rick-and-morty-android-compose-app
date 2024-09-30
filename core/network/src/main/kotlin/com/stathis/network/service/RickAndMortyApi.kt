package com.stathis.network.service

import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto
import com.stathis.network.model.episodes.EpisodeDto
import com.stathis.network.util.CHARACTER_BY_ID_ENDPOINT
import com.stathis.network.util.CHARACTER_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    /**
     * Endpoints for Characters
     */

    @GET(CHARACTER_ENDPOINT)
    suspend fun getAllCharacters(): Response<CharacterWrapperDto>?

    @GET(CHARACTER_BY_ID_ENDPOINT)
    suspend fun getCharacterById(
        @Path(value = "id") id: Int
    ): Response<CharacterResponseDto>?

    /**
     * Endpoints for Episodes
     */

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path(value = "id") id: Int
    ): Response<EpisodeDto>?

    @GET("episode/{id}")
    suspend fun getMultipleEpisodesById(
        @Path(value = "id") ids: List<String>
    ): Response<List<EpisodeDto>>?
}
