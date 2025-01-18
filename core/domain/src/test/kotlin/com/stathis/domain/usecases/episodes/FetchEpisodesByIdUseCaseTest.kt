package com.stathis.domain.usecases.episodes

import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.testing.EpisodeFakes
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
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
        private val GENERIC_ERROR = Result.Error<Any>(Exception("Something went wrong"))
    }

    @Test
    fun `GIVEN valid episode ids, WHEN calling invoke(), THEN return successful result`() = runTest {
        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(EPISODE_ID)
        } returns flowOf(Result.Success(dummyEpisodes))

        testedClass.invoke(EPISODE_ID).collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(dummyEpisodes, result.data)
        }
    }

    @Test
    fun `GIVEN empty episode ids, WHEN calling invoke(), THEN throw Exception with message`() = runTest {
        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(listOf())
        } returns flowOf(Result.Success(listOf()))

        testedClass.invoke(listOf()).catch { exception ->
            assertEquals("Empty episode ids provided.", exception.message)
        }.firstOrNull()
    }

    @Test
    fun `GIVEN valid episode id, WHEN calling invoke() and call fails, THEN return successful result`() = runTest {
        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(EPISODE_ID)
        } returns flowOf(Result.Error(exception = GENERIC_ERROR.exception))

        testedClass.invoke(EPISODE_ID).collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(GENERIC_ERROR.exception.message, result.exception.message)
        }
    }
}
