package com.stathis.domain.usecases.characters

import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.EpisodesRepository
import com.stathis.domain.repository.LocationRepository
import com.stathis.model.Result
import com.stathis.testing.DUMMY_CHARACTER
import com.stathis.testing.DUMMY_EPISODE
import com.stathis.testing.DUMMY_LOCATION
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchCharacterDetailsUseCaseTest {

    private val charactersRepository = mockk<CharactersRepository>()
    private val locationsRepository = mockk<LocationRepository>()
    private val episodesRepository = mockk<EpisodesRepository>()

    private val testedClass = FetchCharacterDetailsUseCase(
        charactersRepository = charactersRepository,
        locationRepository = locationsRepository,
        episodesRepository = episodesRepository
    )

    companion object {

        private val characterId = DUMMY_CHARACTER.id

        private val GENERIC_ERROR = Result.Error<Any>(Exception("Something went wrong"))
    }

    @Test
    fun `GIVEN valid character id, WHEN calling invoke(), THEN return successful result with data`() = runTest {
        coEvery {
            charactersRepository.getCharacterById(characterId)
        } returns flowOf(Result.Success(DUMMY_CHARACTER))

        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(DUMMY_CHARACTER.episode)
        } returns flowOf(Result.Success(listOf(DUMMY_EPISODE)))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.origin.id)
        } returns flowOf(Result.Success(DUMMY_LOCATION))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.location.id)
        } returns flowOf(Result.Success(DUMMY_LOCATION))

        testedClass.invoke(characterId).collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(DUMMY_CHARACTER, result.data.character)
            assertEquals(DUMMY_LOCATION, result.data.originInfo)
            assertEquals(DUMMY_LOCATION, result.data.locationInfo)
            assertEquals(listOf(DUMMY_EPISODE), result.data.episodes)
        }
    }

    @Test
    fun `GIVEN zero character id, WHEN calling invoke(), THEN throw exception with message`() = runTest {
        coEvery {
            charactersRepository.getCharacterById(characterId)
        } returns flowOf(Result.Success(DUMMY_CHARACTER))

        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(DUMMY_CHARACTER.episode)
        } returns flowOf(Result.Success(listOf(DUMMY_EPISODE)))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.origin.id)
        } returns flowOf(Result.Success(DUMMY_LOCATION))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.location.id)
        } returns flowOf(Result.Success(DUMMY_LOCATION))

        testedClass.invoke(0).catch { exception ->
            assertEquals("Character id with zero value provided", exception.message)
        }.firstOrNull()
    }

    @Test
    fun `GIVEN episodes location rainy scenario, WHEN calling invoke(), THEN return error result with exception`() =
        runTest {
            coEvery {
                charactersRepository.getCharacterById(characterId)
            } returns flowOf(Result.Success(DUMMY_CHARACTER))

            coEvery {
                episodesRepository.fetchMultipleEpisodeInfo(DUMMY_CHARACTER.episode)
            } returns flowOf(Result.Error(GENERIC_ERROR.exception))

            coEvery {
                locationsRepository.getLocationById(DUMMY_CHARACTER.origin.id)
            } returns flowOf(Result.Success(DUMMY_LOCATION))

            coEvery {
                locationsRepository.getLocationById(DUMMY_CHARACTER.location.id)
            } returns flowOf(Result.Success(DUMMY_LOCATION))

            testedClass.invoke(characterId).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals(GENERIC_ERROR.exception.message, result.exception.message)
            }
        }

    @Test
    fun `GIVEN origin location rainy scenario, WHEN calling invoke(), THEN return error result with exception`() = runTest {
        coEvery {
            charactersRepository.getCharacterById(characterId)
        } returns flowOf(Result.Success(DUMMY_CHARACTER))

        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(DUMMY_CHARACTER.episode)
        } returns flowOf(Result.Success(listOf(DUMMY_EPISODE)))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.origin.id)
        } returns flowOf(Result.Error(GENERIC_ERROR.exception))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.location.id)
        } returns flowOf(Result.Success(DUMMY_LOCATION))

        testedClass.invoke(characterId).collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(GENERIC_ERROR.exception.message, result.exception.message)
        }
    }

    @Test
    fun `GIVEN current location rainy scenario, WHEN calling invoke(), THEN return error result with exception`() = runTest {
        coEvery {
            charactersRepository.getCharacterById(characterId)
        } returns flowOf(Result.Success(DUMMY_CHARACTER))

        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(DUMMY_CHARACTER.episode)
        } returns flowOf(Result.Success(listOf(DUMMY_EPISODE)))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.origin.id)
        } returns flowOf(Result.Success(DUMMY_LOCATION))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.location.id)
        } returns flowOf(Result.Error(GENERIC_ERROR.exception))

        testedClass.invoke(characterId).collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(GENERIC_ERROR.exception.message, result.exception.message)
        }
    }

    @Test
    fun `GIVEN character call rainy scenario, WHEN calling invoke(), THEN return error result with exception`() = runTest {
        coEvery {
            charactersRepository.getCharacterById(characterId)
        } returns flowOf(Result.Error(GENERIC_ERROR.exception))

        coEvery {
            episodesRepository.fetchMultipleEpisodeInfo(DUMMY_CHARACTER.episode)
        } returns flowOf(Result.Success(listOf(DUMMY_EPISODE)))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.origin.id)
        } returns flowOf(Result.Success(DUMMY_LOCATION))

        coEvery {
            locationsRepository.getLocationById(DUMMY_CHARACTER.location.id)
        } returns flowOf(Result.Success(DUMMY_LOCATION))

        testedClass.invoke(characterId).collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(GENERIC_ERROR.exception.message, result.exception.message)
        }
    }
}
