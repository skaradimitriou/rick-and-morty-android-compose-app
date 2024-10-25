package com.stathis.network.service

import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto
import com.stathis.network.service.RickAndMortyApiTest.Error
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RickAndMortyApiTest {

    private val service: RickAndMortyApi = mockk()

    companion object {

        private const val SUCCESS_CODE: Int = 200
        private const val ERROR_CODE: Int = 400
        private const val GENERIC_ERROR: String = "Generic Error"
    }

    private data class Error<T>(
        val code: Int = ERROR_CODE,
        val errorBody: String = GENERIC_ERROR
    ) {

        val error: Response<T> = Response.error(ERROR_CODE, errorBody.toResponseBody())
    }

    @Test
    fun `test getAllCharacters returns 200 and valid response`() = runTest {
        coEvery { service.getAllCharacters() } returns Response.success(CharacterWrapperDto())
        val result = service.getAllCharacters()
        assertTrue(result.isSuccessful)
        assertTrue(result.body() != null)
        assertEquals(result.code(), SUCCESS_CODE)
    }

    @Test
    fun `test getAllCharacters returns 400 and valid error response`() = runTest {
        coEvery { service.getAllCharacters() } returns Error<CharacterWrapperDto?>().error
        val result = service.getAllCharacters()

        assertFalse(result.isSuccessful)
        assertTrue(result.errorBody() != null)
        assertEquals(result.code(), ERROR_CODE)
        assertTrue(result.errorBody()?.byteStream().toString().contains(GENERIC_ERROR))
    }

    @Test
    fun `test getCharacterById with valid character id returns 200 and valid response`() = runTest {
        val characterId: Int = 1
        val mocked = CharacterResponseDto(id = characterId, name = "Rick")

        coEvery { service.getCharacterById(characterId) } returns Response.success(mocked)
        val result = service.getCharacterById(characterId)

        assertTrue(result.isSuccessful)
        assertTrue(result.body() != null)
        assertEquals(result.code(), SUCCESS_CODE)
        assertEquals(result.body()?.id, mocked.id)
        assertEquals(result.body()?.name, mocked.name)
    }

    @Test
    fun `test getCharacterById with valid character id returns 400 and valid error body`() = runTest {
        val characterId: Int = 1

        coEvery { service.getCharacterById(characterId) } returns Error<CharacterResponseDto?>().error
        val result = service.getCharacterById(characterId)

        assertFalse(result.isSuccessful)
        assertTrue(result.errorBody() != null)
        assertEquals(result.code(), ERROR_CODE)
        assertTrue(result.errorBody()?.byteStream().toString().contains(GENERIC_ERROR))
    }

    @Test
    fun `test getMultipleCharactersById with valid characters id returns 200 and valid response`() = runTest {
        val characterIds: List<String> = listOf("1", "2", "3")
        val fakeCharacters = listOf<CharacterResponseDto>()
        coEvery { service.getMultipleCharactersById(characterIds) } returns Response.success(fakeCharacters)
        val result = service.getMultipleCharactersById(characterIds)

        assertTrue(result.isSuccessful)
        assertTrue(result.body() != null && result.body() is List<CharacterResponseDto>)
        assertEquals(result.code(), SUCCESS_CODE)
    }

    @Test
    fun `test getMultipleCharactersById with valid characters id returns 400 and valid error body`() = runTest {
        val characterIds: List<String> = listOf("1", "2", "3")
        coEvery { service.getMultipleCharactersById(characterIds) } returns Error<List<CharacterResponseDto>?>().error
        val result = service.getMultipleCharactersById(characterIds)

        assertFalse(result.isSuccessful)
        assertTrue(result.errorBody() != null)
        assertEquals(result.code(), ERROR_CODE)
        assertTrue(result.errorBody()?.byteStream().toString().contains(GENERIC_ERROR))
    }
}
