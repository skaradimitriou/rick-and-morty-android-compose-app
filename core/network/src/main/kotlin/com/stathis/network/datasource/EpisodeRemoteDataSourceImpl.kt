package com.stathis.network.datasource

import com.stathis.network.BuildConfig
import com.stathis.network.model.NetworkResult
import com.stathis.network.model.episodes.EpisodeDto
import com.stathis.network.model.episodes.EpisodeWrapperDto
import com.stathis.network.util.EPISODE_ENDPOINT
import com.stathis.network.util.NAME_PARAMETER
import com.stathis.network.util.PATH
import com.stathis.network.util.mapApiCallToNetworkResult
import com.stathis.network.util.toFullApiCallUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class EpisodeRemoteDataSourceImpl(
    private val client: HttpClient
) : EpisodesRemoteDataSource {

    companion object {

        private val URL = BuildConfig.API_URL.toFullApiCallUrl(endpoint = EPISODE_ENDPOINT)
    }

    override suspend fun fetchEpisodeById(id: Int): NetworkResult<EpisodeDto?> {
        val call = client.get(URL.plus(PATH).plus(id.toString()))
        return client.mapApiCallToNetworkResult<EpisodeDto?>(call = call)
    }

    override suspend fun fetchEpisodeByName(name: String): NetworkResult<EpisodeWrapperDto?> {
        val call = client.get(URL) { parameter(NAME_PARAMETER, name) }
        return client.mapApiCallToNetworkResult<EpisodeWrapperDto?>(call = call)
    }

    override suspend fun fetchMultipleEpisodesById(ids: List<String>): NetworkResult<List<EpisodeDto>?> {
        val call = client.get(URL.plus(PATH).plus(ids))
        return client.mapApiCallToNetworkResult<List<EpisodeDto>>(call = call)
    }
}
