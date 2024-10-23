package com.stathis.domain.usecases.episodes

import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.testing.EpisodeFakes
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchEpisodesByIdUseCaseTest {

    private val episodesRepository = mockk<EpisodesRepository>()
    private val testedClass = FetchEpisodesByIdUseCase(episodesRepository)

    companion object {

        private val EPISODE_ID = listOf("123")

        private val dummyEpisodes = EpisodeFakes.provideDummyEpisodeList()
        private val GENERIC_ERROR = Result.Error<Any>(errorCode = 404, message = "Resource not found")
    }

    @Test
    fun `test invoke with valid id returns successful result with data`() = runTest {
        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(EPISODE_ID)
        } returns flowOf(Result.Success(dummyEpisodes))

        testedClass.invoke(EPISODE_ID).collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(result.data, dummyEpisodes)
        }
    }

    @Test
    fun `test invoke with valid id returns successful result with empty list`() = runTest {
        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(EPISODE_ID)
        } returns flowOf(Result.Success(listOf()))

        testedClass.invoke(EPISODE_ID).collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(result.data, listOf())
        }
    }

    @Test
    fun `test invoke with null id returns successful result`() = runTest {
        //FIXME: Add check that if the query is empty it should return error result.
        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(listOf())
        } returns flowOf(Result.Success(listOf()))

        testedClass.invoke("").collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(result.data, listOf())
        }
    }

    @Test
    fun `test invoke with valid id returns error result`() = runTest {
        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(EPISODE_ID)
        } returns flowOf(Result.Error(errorCode = GENERIC_ERROR.errorCode, message = GENERIC_ERROR.message))

        testedClass.invoke(EPISODE_ID).collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(result.errorCode, GENERIC_ERROR.errorCode)
            assertEquals(result.message, GENERIC_ERROR.message)
        }
    }
}
