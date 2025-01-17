package com.stathis.domain.usecases.episodes

import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.testing.CharactersFakes
import com.stathis.testing.EpisodeFakes
import com.stathis.util.util.toNotNull
import io.mockk.coEvery
import io.mockk.mockk
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

        private val dummyEpisode = EpisodeFakes.provideDummyEpisode()
        private val dummyCharacters = CharactersFakes.provideDummyCharacterList()
        private val episodeId = dummyEpisode.id

        private val GENERIC_ERROR = Result.Error<Any>(Exception("Something went wrong"))
    }

    @Test
    fun `GIVEN valid episode id, WHEN calling invoke(), THEN return successful result`() = runTest {
        val expected = FetchEpisodeDetailsUseCase.EpisodeDetails(
            episode = dummyEpisode,
            characters = dummyCharacters
        )

        coEvery {
            episodesRepository.fetchEpisodeInfo(episodeId)
        } returns flowOf(Result.Success(data = dummyEpisode))

        coEvery {
            charactersRepository.getMultipleCharacterById(dummyEpisode.characters)
        } returns flowOf(Result.Success(data = dummyCharacters))

        testedClass.invoke(episodeId).collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(expected, result.data)
        }
    }

    @Test
    fun `GIVEN null episode id, WHEN calling invoke(), THEN return successful result`() = runTest {
        //FIXME: If the episodeId is null, then the usecase should return error result

        val episodeId: Int? = null

        coEvery {
            episodesRepository.fetchEpisodeInfo(episodeId.toNotNull())
        } returns flowOf(Result.Success(data = dummyEpisode))

        coEvery {
            charactersRepository.getMultipleCharacterById(dummyEpisode.characters)
        } returns flowOf(Result.Success(data = dummyCharacters))

        testedClass.invoke(episodeId).collect { result ->
            assertTrue(result is Result.Success)
        }
    }

    @Test
    fun `GIVEN valid episode id, WHEN calling invoke() and getMultipleCharacterById fails, THEN return error result result`() =
        runTest {
            //FIXME: That case should return successful result with empty character list
            coEvery {
                episodesRepository.fetchEpisodeInfo(episodeId)
            } returns flowOf(Result.Success(data = dummyEpisode))

            coEvery {
                charactersRepository.getMultipleCharacterById(dummyEpisode.characters)
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
