package com.stathis.network.service

import com.stathis.network.model.characters.CharacterWrapperDto
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterWrapperDto>?
}