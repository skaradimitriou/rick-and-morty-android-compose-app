package com.stathis.domain.usecases.locations

import com.stathis.domain.repository.CharactersRepository
import com.stathis.domain.repository.LocationRepository
import com.stathis.model.Result
import com.stathis.testing.DUMMY_CHARACTER
import com.stathis.testing.DUMMY_LOCATION
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchLocationInfoByIdUseCaseTest {

    private val locationsRepository = mockk<LocationRepository>()
    private val charactersRepository = mockk<CharactersRepository>()

    private val testedClass = FetchLocationInfoByIdUseCase(
        locationRepository = locationsRepository,
        charactersRepository = charactersRepository
    )

    @Test
    fun `GIVEN valid location id, WHEN calling invoke(), THEN return successful result with full data`() = runTest {
        coEvery {
            locationsRepository.getLocationById(DUMMY_LOCATION.id)
        } returns flowOf(Result.Success(data = DUMMY_LOCATION))

        coEvery {
            charactersRepository.getMultipleCharacterById(DUMMY_LOCATION.residents)
        } returns flowOf(Result.Success(data = listOf(DUMMY_CHARACTER)))

        val expected = Result.Success(
            FetchLocationInfoByIdUseCase.LocationInformationResult(
                locationInfo = DUMMY_LOCATION,
                residents = listOf(DUMMY_CHARACTER)
            )
        )

        testedClass.invoke(DUMMY_LOCATION.id).collect { result ->
            assertTrue(result is Result.Success<FetchLocationInfoByIdUseCase.LocationInformationResult>)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `GIVEN zero location id, WHEN calling invoke(), THEN return failure result with exception`() = runTest {
        //FIXME: When calling invoke with 0 as a location Id, then return failure result.
        coEvery {
            locationsRepository.getLocationById(0)
        } returns flowOf(Result.Success(data = DUMMY_LOCATION))

        coEvery {
            charactersRepository.getMultipleCharacterById(listOf())
        } returns flowOf(Result.Success(data = listOf(DUMMY_CHARACTER)))

        val expected = Result.Success(
            FetchLocationInfoByIdUseCase.LocationInformationResult(
                locationInfo = DUMMY_LOCATION,
                residents = listOf(DUMMY_CHARACTER)
            )
        )

        testedClass.invoke(0).collect { result ->
            assertTrue(result is Result.Success<FetchLocationInfoByIdUseCase.LocationInformationResult>)
            assertEquals(expected, result)
        }
    }

    @Test
    fun `GIVEN valid location id, WHEN calling invoke() and characters call fails, THEN return failure result with exception`() =
        runTest {
            coEvery {
                locationsRepository.getLocationById(DUMMY_LOCATION.id)
            } returns flowOf(Result.Success(data = DUMMY_LOCATION))

            coEvery {
                charactersRepository.getMultipleCharacterById(DUMMY_LOCATION.residents)
            } returns flowOf(Result.Error(exception = Exception("Something went wrong")))


            testedClass.invoke(DUMMY_LOCATION.id).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals("Something went wrong", result.exception.message)
            }
        }

    @Test
    fun `GIVEN valid location id, WHEN calling invoke() and location call fails, THEN return failure result with exception`() =
        runTest {
            coEvery {
                locationsRepository.getLocationById(DUMMY_LOCATION.id)
            } returns flowOf(Result.Error(exception = Exception("Something went wrong")))

            coEvery {
                charactersRepository.getMultipleCharacterById(DUMMY_LOCATION.residents)
            } returns flowOf(Result.Success(data = listOf(DUMMY_CHARACTER)))


            testedClass.invoke(DUMMY_LOCATION.id).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals("Something went wrong", result.exception.message)
            }
        }

    @Test
    fun `GIVEN valid location id, WHEN calling invoke() and both call fail, THEN return failure result with exception`() =
        runTest {
            coEvery {
                locationsRepository.getLocationById(DUMMY_LOCATION.id)
            } returns flowOf(Result.Error(exception = Exception("Something went wrong")))

            coEvery {
                charactersRepository.getMultipleCharacterById(DUMMY_LOCATION.residents)
            } returns flowOf(Result.Error(exception = Exception("Something went wrong")))

            testedClass.invoke(DUMMY_LOCATION.id).collect { result ->
                assertTrue(result is Result.Error)
                assertEquals("Something went wrong", result.exception.message)
            }
        }
}
