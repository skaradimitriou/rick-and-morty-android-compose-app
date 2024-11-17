package com.stathis.data.util

import com.stathis.data.mapper.characters.CharacterResponseMapper
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import com.stathis.network.model.characters.CharacterResponseDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MappingExtTest {

    private val testDispatcher = StandardTestDispatcher()

    companion object {

        private const val GENERIC_ERROR_MESSAGE = "MappingExtTest"
        private const val GENERIC_ERROR_CODE = 404
        private const val GENERIC_THROWABLE_MESSAGE = "Testing class"
    }

    @Test
    fun `given successful response, when calling mapToDomainResult return successful domain result of model`() =
        runTest(testDispatcher) {
            val dto: CharacterResponseDto = CharacterResponseDto(
                id = 123,
                name = "Character Name"
            )
            
            val response = Response.success(dto)

            val result = mapToDomainResult(
                networkCall = { response },
                mapping = { CharacterResponseMapper.toDomainModel(dto) }
            )

            assertTrue(result is Result.Success)
            assertEquals(result.data.id, dto.id)
            assertEquals(result.data.name, dto.name)
        }

    @Test
    fun `given failure response, when calling mapToDomainResult return failure domain result with error info`() =
        runTest(testDispatcher) {
            val response = Response.error<CharacterResponseDto>(GENERIC_ERROR_CODE, GENERIC_ERROR_MESSAGE.toResponseBody())

            val result = mapToDomainResult(
                networkCall = { response },
                mapping = { CharacterResponseMapper.toDomainModel(it) }
            )

            assertTrue(response.errorBody() != null)
            assertTrue(result is Result.Error)
            assertEquals(result.errorCode, GENERIC_ERROR_CODE)
        }

    @Test
    fun `given exception, when calling mapToDomainResult return failure domain result with error info`() =
        runTest(testDispatcher) {
            val response: suspend () -> Response<CharacterResponseDto> = mockk()
            val exception = Exception(GENERIC_ERROR_MESSAGE, Throwable(GENERIC_THROWABLE_MESSAGE))
            coEvery { response.invoke() } throws exception

            val result: Result<CharacterResponse> = mapToDomainResult(
                networkCall = response,
                mapping = { CharacterResponseMapper.toDomainModel(it) }
            )

            assertTrue(result is Result.Error)
            assertEquals(result.errorCode, GENERIC_ERROR_CODE)
            assertEquals(result.message, GENERIC_ERROR_MESSAGE)
        }
}
