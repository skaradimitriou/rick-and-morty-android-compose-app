package com.stathis.data.repository

import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.network.datasource.EpisodesRemoteDataSource
import com.stathis.network.model.NetworkResult
import com.stathis.network.model.episodes.EpisodeDto
import com.stathis.network.model.episodes.EpisodeWrapperDto
import com.stathis.util.errors.NetworkError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodesRepositoryTest {

    private val api: EpisodesRemoteDataSource = mockk()
    private lateinit var repository: EpisodesRepository

    private val dispatcher: CoroutineDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        repository = EpisodesRepositoryImpl(remoteDataSource = api)
    }

    companion object {

        private const val DUMMY_EPISODE_ID = 1
        private const val DUMMY_EPISODE_NAME = "Awesome Episode"

        private const val ERROR_CODE: Int = 400
        private const val ERROR_MSG = "Generic Error"

        private val DUMMY_EPISODE_DTO_MODEL: EpisodeDto = EpisodeDto(
            id = DUMMY_EPISODE_ID,
            name = DUMMY_EPISODE_NAME,
            air_date = "XX/XX/2024",
            episode = "ABC",
            characters = listOf("abc/1", "abc/2", "abc/3"),
            url = "www.episode.com",
            created = "XX/XX/2024"
        )
    }

    @Test
    fun `given valid episode id, when calling fetchEpisodeInfo, then return successful mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult<EpisodeDto?> = NetworkResult.Success(DUMMY_EPISODE_DTO_MODEL)
            coEvery { api.fetchEpisodeById(DUMMY_EPISODE_ID) } returns response

            repository.fetchEpisodeInfo(DUMMY_EPISODE_ID).collect { result ->
                assertTrue(result is Result.Success)

                assertEquals(DUMMY_EPISODE_DTO_MODEL.id, result.data.id)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.name, result.data.name)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.air_date, result.data.airDate)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.episode, result.data.episode)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.characters?.map { it.substringAfter("/") }, result.data.characters)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.url, result.data.url)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.created, result.data.created)
            }
        }

    @Test
    fun `given valid episode id, when calling fetchEpisodeInfo, then return failure mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult<EpisodeDto?> = NetworkResult.Error<EpisodeDto?>(ERROR_CODE, ERROR_MSG)
            coEvery { api.fetchEpisodeById(DUMMY_EPISODE_ID) } returns response

            repository.fetchEpisodeInfo(DUMMY_EPISODE_ID).collect { result ->
                assertTrue(result is Result.Error && result.exception is NetworkError.Generic)
                with(result.exception) {
                    assertEquals(ERROR_CODE, (this as NetworkError.Generic).errorCode)
                    assertTrue(message.toString().isNotEmpty())
                }
            }
        }

    @Test
    fun `given valid episode name, when calling fetchEpisodesByName, then return successful mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult<EpisodeWrapperDto?> = NetworkResult.Success(
                body = EpisodeWrapperDto(results = listOf(DUMMY_EPISODE_DTO_MODEL))
            )

            coEvery { api.fetchEpisodeByName(DUMMY_EPISODE_NAME) } returns response

            repository.fetchEpisodesByName(DUMMY_EPISODE_NAME).collect { result ->
                assertTrue(result is Result.Success)

                assertEquals(DUMMY_EPISODE_DTO_MODEL.id, result.data.first().id)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.name, result.data.first().name)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.air_date, result.data.first().airDate)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.episode, result.data.first().episode)
                assertEquals(
                    DUMMY_EPISODE_DTO_MODEL.characters?.map { it.substringAfter("/") },
                    result.data.first().characters
                )
                assertEquals(DUMMY_EPISODE_DTO_MODEL.url, result.data.first().url)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.created, result.data.first().created)
            }
        }

    @Test
    fun `given valid episode name, when calling fetchEpisodesByName, then return failure mapped domain result`() =
        runTest(dispatcher) {
            val response = NetworkResult.Error<EpisodeWrapperDto?>(ERROR_CODE, ERROR_MSG)
            coEvery { api.fetchEpisodeByName(DUMMY_EPISODE_NAME) } returns response

            repository.fetchEpisodesByName(DUMMY_EPISODE_NAME).collect { result ->
                assertTrue(result is Result.Error && result.exception is NetworkError.Generic)
                with(result.exception) {
                    assertEquals(ERROR_CODE, (this as NetworkError.Generic).errorCode)
                    assertTrue(message.toString().isNotEmpty())
                }
            }
        }

    @Test
    fun `given valid episode ids, when calling fetchMultipleEpisodeInfoById, then return successful mapped domain result`() =
        runTest(dispatcher) {
            val response: NetworkResult.Success<List<EpisodeDto>?> = NetworkResult.Success(listOf(DUMMY_EPISODE_DTO_MODEL))
            coEvery { api.fetchMultipleEpisodesById(listOf(DUMMY_EPISODE_ID.toString())) } returns response

            repository.fetchMultipleEpisodeInfo(listOf(DUMMY_EPISODE_ID.toString())).collect { result ->
                assertTrue(result is Result.Success)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.id, result.data.first().id)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.name, result.data.first().name)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.air_date, result.data.first().airDate)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.episode, result.data.first().episode)
                assertEquals(
                    DUMMY_EPISODE_DTO_MODEL.characters?.map { it.substringAfter("/") },
                    result.data.first().characters
                )
                assertEquals(DUMMY_EPISODE_DTO_MODEL.url, result.data.first().url)
                assertEquals(DUMMY_EPISODE_DTO_MODEL.created, result.data.first().created)
            }
        }

    @Test
    fun `given valid episode ids, when calling fetchMultipleEpisodeInfoById, then return failure mapped domain result`() =
        runTest(dispatcher) {
            val response = NetworkResult.Error<List<EpisodeDto>?>(ERROR_CODE, ERROR_MSG)
            coEvery { api.fetchMultipleEpisodesById(listOf(DUMMY_EPISODE_ID.toString())) } returns response

            repository.fetchMultipleEpisodeInfo(listOf(DUMMY_EPISODE_ID.toString())).collect { result ->
                assertTrue(result is Result.Error && result.exception is NetworkError.Generic)
                with(result.exception) {
                    assertEquals(ERROR_CODE, (this as NetworkError.Generic).errorCode)
                    assertTrue(message.toString().isNotEmpty())
                }
            }
        }
}
