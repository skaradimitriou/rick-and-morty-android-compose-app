package com.stathis.network.datasource

import com.stathis.network.model.NetworkResult
import com.stathis.network.model.location.LocationDto
import com.stathis.network.model.location.LocationWrapperDto

/**
 * Remote DataSource that fetches all Location related data.
 */
interface LocationsRemoteDataSource {

    /**
     * Fetches a single [com.stathis.network.model.location.LocationDto] by its id.
     * @param id: The id of the location that will be fetched from the remote source.
     */
    suspend fun fetchLocationById(id: Int): NetworkResult<LocationDto?>

    /**
     * Fetches a single [com.stathis.network.model.location.LocationDto] by its name.
     * @param name: The name of the location that will be fetched from the remote source.
     */
    suspend fun fetchLocationByName(name: String): NetworkResult<LocationWrapperDto?>

    /**
     * Fetches multiple [com.stathis.network.model.location.LocationDto] by their id.
     * @param ids: A list containing the location ids that will be fetched from the remote source.
     */
    suspend fun fetchMultipleLocationsById(ids: List<String>): NetworkResult<List<LocationDto>?>
}
