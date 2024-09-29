package com.stathis.network.service

import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterWrapperDto>?

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path(value = "id") id: Int
    ): Response<CharacterResponseDto>?
}