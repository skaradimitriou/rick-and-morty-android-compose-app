package com.stathis.domain.usecases.characters

import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.model.Result
import com.stathis.testing.CharactersFakes
import com.stathis.testing.EpisodeFakes
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class FetchCharacterDetailsUseCaseTest {

    private val charactersRepository = mockk<CharactersRepository>()
    private val episodesRepository = mockk<EpisodesRepository>()

    private val testedClass = FetchCharacterDetailsUseCase(
        charactersRepository = charactersRepository,
        episodesRepository = episodesRepository
    )

    companion object {

        private val dummyCharacter = CharactersFakes.provideDummyCharacter()
        private val dummyEpisodes = EpisodeFakes.provideDummyEpisodeList()
        private val characterId = dummyCharacter.id

        private val GENERIC_ERROR = Result.Error<Any>(errorCode = 404, message = "Resource not found")
    }

    @Test
    fun `test invoke with valid characterId returns both successful result`() = runTest {
        coEvery {
            charactersRepository.getCharacterById(characterId)
        } returns flowOf(Result.Success(dummyCharacter))

        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(dummyCharacter.episode)
        } returns flowOf(Result.Success(dummyEpisodes))

        testedClass.invoke(characterId).collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(result.data.character, dummyCharacter)
            assertEquals(result.data.episodes, dummyEpisodes)
        }
    }

    @Test
    fun `test invoke with valid characterId returns failed result due to result not found`() = runTest {
        coEvery {
            charactersRepository.getCharacterById(characterId)
        } returns flowOf(
            Result.Error(
                errorCode = GENERIC_ERROR.errorCode,
                message = GENERIC_ERROR.message
            )
        )

        testedClass.invoke(characterId).collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(result.errorCode, GENERIC_ERROR.errorCode)
            assertEquals(result.message, GENERIC_ERROR.message)
        }
    }

    @Test
    fun `test invoke with valid characterId returns successful details and failed episodes result`() = runTest {
        coEvery {
            charactersRepository.getCharacterById(characterId)
        } returns flowOf(Result.Success(dummyCharacter))

        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(dummyCharacter.episode)
        } returns flowOf(
            Result.Error(
                errorCode = GENERIC_ERROR.errorCode,
                message = GENERIC_ERROR.message
            )
        )

        testedClass.invoke(characterId).collect { result ->
            //FIXME: Use case in that case should return successful result and show only the characterDetails
            assertTrue(result is Result.Error)
            assertEquals(result.errorCode, GENERIC_ERROR.errorCode)
            assertEquals(result.message, GENERIC_ERROR.message)
        }
    }
}
