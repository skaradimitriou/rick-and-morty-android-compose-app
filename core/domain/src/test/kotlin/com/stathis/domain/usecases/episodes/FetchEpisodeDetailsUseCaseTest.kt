package com.stathis.domain.usecases.episodes

import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.testing.DUMMY_CHARACTER
import com.stathis.testing.DUMMY_EPISODE
import com.stathis.util.util.toNotNull
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchEpisodeDetailsUseCaseTest {

    private val episodesRepository = mockk<EpisodesRepository>()
    private val charactersRepository = mockk<CharactersRepository>()

    private val testedClass = FetchEpisodeDetailsUseCase(
        episodesRepository = episodesRepository,
        charactersRepository = charactersRepository
    )

    companion object {

        private val dummyCharacters = listOf(DUMMY_CHARACTER)
        private val episodeId = DUMMY_EPISODE.id

        private val GENERIC_ERROR = Result.Error<Any>(Exception("Something went wrong"))
    }

    @Test
    fun `GIVEN valid episode id, WHEN calling invoke(), THEN return successful result`() = runTest {
        val expected = FetchEpisodeDetailsUseCase.EpisodeDetails(
            episode = DUMMY_EPISODE,
            characters = dummyCharacters
        )

        coEvery {
            episodesRepository.fetchEpisodeInfo(episodeId)
        } returns flowOf(Result.Success(data = DUMMY_EPISODE))

        coEvery {
            charactersRepository.getMultipleCharacterById(DUMMY_EPISODE.characters)
        } returns flowOf(Result.Success(data = dummyCharacters))

        testedClass.invoke(episodeId).collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(expected, result.data)
        }
    }

    @Test
    fun `GIVEN zero episode id, WHEN calling invoke(), THEN throw exception with message`() = runTest {
        coEvery {
            episodesRepository.fetchEpisodeInfo(episodeId.toNotNull())
        } returns flowOf(Result.Success(data = DUMMY_EPISODE))

        coEvery {
            charactersRepository.getMultipleCharacterById(DUMMY_EPISODE.characters)
        } returns flowOf(Result.Success(data = dummyCharacters))

        testedClass.invoke(0).catch { exception ->
            assertEquals("Episode id with zero value provided", exception.message)
        }.firstOrNull()
    }

    @Test
    fun `GIVEN valid episode id, WHEN calling invoke() and getMultipleCharacterById fails, THEN return error result result`() =
        runTest {
            //FIXME: That case should return successful result with empty character list
            coEvery {
                episodesRepository.fetchEpisodeInfo(episodeId)
            } returns flowOf(Result.Success(data = DUMMY_EPISODE))

            coEvery {
                charactersRepository.getMultipleCharacterById(DUMMY_EPISODE.characters)
            } returns flowOf(
                Result.Error(GENERIC_ERROR.exception)
            )

            testedClass.invoke(episodeId).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals(GENERIC_ERROR.exception.message, result.exception.message)
            }
        }

    @Test
    fun `GIVEN valid episode id, WHEN calling invoke() and fetchEpisodeInfo fails, THEN return error result result`() =
        runTest {
            coEvery { episodesRepository.fetchEpisodeInfo(episodeId) } returns flowOf(
                Result.Error(GENERIC_ERROR.exception)
            )

            testedClass.invoke(episodeId).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals(GENERIC_ERROR.exception.message, result.exception.message)
            }
        }
}
