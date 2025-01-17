package com.stathis.domain.usecases.characters

import com.stathis.domain.repository.CharactersRepository
import com.stathis.model.Result
import com.stathis.testing.CharactersFakes
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchAllCharactersUseCaseTest {

    private val charactersRepository = mockk<CharactersRepository>()

    private lateinit var testedClass: FetchAllCharactersUseCase

    companion object {

        private val dummyCharacters = CharactersFakes.provideDummyCharacterList()
        private val GENERIC_ERROR = Result.Error<Any>(Exception("Something went wrong"))
    }

    @Before
    fun setup() {
        testedClass = FetchAllCharactersUseCase(charactersRepository)
    }

    @Test
    fun `GIVEN happy scenario, WHEN calling invoke() fun, THEN return successful result with a list of characters`() =
        runTest {
            coEvery { charactersRepository.getAllCharacters() } returns flowOf(Result.Success(dummyCharacters))

            testedClass.invoke().collect { result ->
                assertTrue(result is Result.Success)
                assertEquals(dummyCharacters, result.data)
            }
        }

    @Test
    fun `GIVEN rainy scenario, WHEN calling invoke() fun, THEN return error result with an exception`() = runTest {
        coEvery { charactersRepository.getAllCharacters() } returns flowOf(
            Result.Error(exception = GENERIC_ERROR.exception)
        )

        testedClass.invoke().collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(GENERIC_ERROR.exception, result.exception)
        }
    }
}
