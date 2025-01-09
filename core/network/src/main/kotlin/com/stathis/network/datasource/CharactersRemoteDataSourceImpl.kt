package com.stathis.network.datasource

import com.stathis.network.BuildConfig
import com.stathis.network.model.NetworkResult
import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto
import com.stathis.network.util.CHARACTER_ENDPOINT
import com.stathis.network.util.mapApiCallToNetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.buildUrl
import java.net.URI

internal class CharactersRemoteDataSourceImpl(
    private val client: HttpClient
) : CharactersRemoteDataSource {

    companion object {

        private val API_URL_URI = URI(BuildConfig.API_URL.plus(CHARACTER_ENDPOINT))

        private val URL = buildUrl {
            protocol = URLProtocol.HTTPS
            host = API_URL_URI.host.plus(API_URL_URI.path)
        }.toString()

        private const val PATH: String = "/"
        private const val NAME_PARAMETER: String = "name"
    }

    override suspend fun fetchAllCharacters(): NetworkResult<CharacterWrapperDto?> {
        return client.mapApiCallToNetworkResult<CharacterWrapperDto?>(call = client.get(URL))
    }

    override suspend fun fetchCharacterById(id: Int): NetworkResult<CharacterResponseDto?> {
        val call = client.get(URL.plus(PATH).plus(id.toString()))
        return client.mapApiCallToNetworkResult<CharacterResponseDto?>(call = call)
    }

    override suspend fun fetchMultipleCharactersById(vararg ids: String): NetworkResult<List<CharacterResponseDto>?> {
        val call = client.get(URL.plus(PATH).plus(ids.joinToString()))
        return client.mapApiCallToNetworkResult<List<CharacterResponseDto>?>(call = call)
    }

    override suspend fun fetchCharacterByName(name: String): NetworkResult<CharacterWrapperDto?> {
        val call = client.get(URL) { parameter(NAME_PARAMETER, name) }
        return client.mapApiCallToNetworkResult<CharacterWrapperDto?>(call = call)
    }
}
