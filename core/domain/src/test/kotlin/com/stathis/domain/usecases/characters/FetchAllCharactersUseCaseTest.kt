package com.stathis.domain.usecases.characters

import com.stathis.domain.repository.CharactersRepository
import com.stathis.model.Result
import com.stathis.testing.CharactersFakes
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchAllCharactersUseCaseTest {

    private val charactersRepository = mockk<CharactersRepository>()

    private val testedClass = FetchAllCharactersUseCase(charactersRepository)

    companion object {

        private val dummyCharacters = CharactersFakes.provideDummyCharacterList()
        private val GENERIC_ERROR = Result.Error<Any>(errorCode = 404, message = "Resource not found")
    }

    @Test
    fun `test invoke returns successful result with a list of characters`() = runTest {
        coEvery { charactersRepository.getAllCharacters() } returns flowOf(Result.Success(dummyCharacters))

        testedClass.invoke().collect { result ->
            assertTrue(result is Result.Success)
            assertEquals(result.data, dummyCharacters)
        }
    }

    @Test
    fun `test invoke returns error generic result`() = runTest {
        coEvery { charactersRepository.getAllCharacters() } returns flowOf(
            Result.Error(
                errorCode = GENERIC_ERROR.errorCode,
                message = GENERIC_ERROR.message
            )
        )

        testedClass.invoke().collect { result ->
            assertTrue(result is Result.Error)
            assertEquals(result.errorCode, GENERIC_ERROR.errorCode)
            assertEquals(result.message, GENERIC_ERROR.message)
        }
    }
}
