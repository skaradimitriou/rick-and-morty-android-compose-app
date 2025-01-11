package com.stathis.network.datasource

import com.stathis.network.model.NetworkResult
import com.stathis.network.model.episodes.EpisodeDto
import com.stathis.network.model.episodes.EpisodeWrapperDto

/**
 * Remote DataSource that fetches all Episode related data.
 */

interface EpisodesRemoteDataSource {

    /**
     * Fetches a single [com.stathis.network.model.episodes.EpisodeDto] by its id.
     * @param id: The id of the episode that will be fetched from the remote source.
     */
    suspend fun fetchEpisodeById(id: Int): NetworkResult<EpisodeDto?>

    /**
     * Fetches a single [com.stathis.network.model.episodes.EpisodeDto] by its name.
     * @param name: The name of the episode that will be fetched from the remote source.
     */
    suspend fun fetchEpisodeByName(name: String): NetworkResult<EpisodeWrapperDto?>

    /**
     * Fetches multiple [com.stathis.network.model.episodes.EpisodeDto] by their id.
     * @param ids: A list containing the episode ids that will be fetched from the remote source.
     */
    suspend fun fetchMultipleEpisodesById(ids: List<String>): NetworkResult<List<EpisodeDto>?>
}
