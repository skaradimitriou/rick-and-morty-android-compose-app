package com.stathis.network.datasource

import com.stathis.network.BuildConfig
import com.stathis.network.model.NetworkResult
import com.stathis.network.model.location.LocationDto
import com.stathis.network.model.location.LocationWrapperDto
import com.stathis.network.util.LOCATION_ENDPOINT
import com.stathis.network.util.NAME_PARAMETER
import com.stathis.network.util.PATH
import com.stathis.network.util.mapApiCallToNetworkResult
import com.stathis.network.util.toFullApiCallUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class LocationsRemoteDataSourceImpl(
    private val client: HttpClient
) : LocationsRemoteDataSource {

    companion object {

        private val URL = BuildConfig.API_URL.toFullApiCallUrl(endpoint = LOCATION_ENDPOINT)
    }

    override suspend fun fetchLocationById(id: Int): NetworkResult<LocationDto?> {
        val call = client.get(URL.plus(PATH).plus(id.toString()))
        return client.mapApiCallToNetworkResult<LocationDto?>(call = call)
    }

    override suspend fun fetchLocationByName(name: String): NetworkResult<LocationWrapperDto?> {
        val call = client.get(URL) { parameter(NAME_PARAMETER, name) }
        return client.mapApiCallToNetworkResult<LocationWrapperDto?>(call = call)
    }

    override suspend fun fetchMultipleLocationsById(ids: List<String>): NetworkResult<List<LocationDto>?> {
        val call = client.get(URL.plus(PATH).plus(ids.joinToString()))
        return client.mapApiCallToNetworkResult<List<LocationDto>>(call = call)
    }
}
