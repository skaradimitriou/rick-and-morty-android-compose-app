package com.stathis.data.repository

import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.network.model.episodes.EpisodeDto
import com.stathis.network.model.episodes.EpisodeWrapperDto
import com.stathis.network.service.RickAndMortyApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodesRepositoryTest {

    private val api: RickAndMortyApi = mockk()
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
            val response: Response<EpisodeDto?> = Response.success(DUMMY_EPISODE_DTO_MODEL)
            coEvery { api.getEpisodeById(DUMMY_EPISODE_ID) } returns response

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
            val response: Response<EpisodeDto?> = Response.error<EpisodeDto?>(ERROR_CODE, ERROR_MSG.toResponseBody())
            coEvery { api.getEpisodeById(DUMMY_EPISODE_ID) } returns response

            repository.fetchEpisodeInfo(DUMMY_EPISODE_ID).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals(ERROR_CODE, result.errorCode)
                assertTrue(result.message.isNotEmpty())
            }
        }

    @Test
    fun `given valid episode name, when calling fetchEpisodesByName, then return successful mapped domain result`() =
        runTest(dispatcher) {
            val response = Response.success(EpisodeWrapperDto(results = listOf(DUMMY_EPISODE_DTO_MODEL)))
            coEvery { api.getEpisodesByName(DUMMY_EPISODE_NAME) } returns response

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
            val response = Response.error<EpisodeWrapperDto?>(ERROR_CODE, ERROR_MSG.toResponseBody())
            coEvery { api.getEpisodesByName(DUMMY_EPISODE_NAME) } returns response

            repository.fetchEpisodesByName(DUMMY_EPISODE_NAME).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals(ERROR_CODE, result.errorCode)
                assertTrue(result.message.isNotEmpty())
            }
        }

    @Test
    fun `given valid episode ids, when calling fetchMultipleEpisodeInfoById, then return successful mapped domain result`() =
        runTest(dispatcher) {
            val response: Response<List<EpisodeDto?>> = Response.success(listOf(DUMMY_EPISODE_DTO_MODEL))
            coEvery { api.getMultipleEpisodesById(listOf(DUMMY_EPISODE_ID.toString())) } returns response

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
            val response = Response.error<List<EpisodeDto?>>(ERROR_CODE, ERROR_MSG.toResponseBody())
            coEvery { api.getMultipleEpisodesById(listOf(DUMMY_EPISODE_ID.toString())) } returns response

            repository.fetchMultipleEpisodeInfo(listOf(DUMMY_EPISODE_ID.toString())).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals(ERROR_CODE, result.errorCode)
                assertTrue(result.message.isNotEmpty())
            }
        }
}
