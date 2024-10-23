package com.stathis.domain.usecases.search

import com.stathis.domain.repository.QueriesRepository
import com.stathis.model.search.Query
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FetchAllUserQueriesUseCaseTest {

    private val queriesRepository = mockk<QueriesRepository>()

    private val testedClass = FetchAllUserQueriesUseCase(queriesRepository)

    @Test
    fun `test invoke returns successful result with list of user queries`() = runTest {
        val expected = listOf(Query("Rick"), Query("Morty"))
        coEvery {
            queriesRepository.fetchAllUserQueries()
        } returns flowOf(expected)

        testedClass.invoke().collect { result ->
            assertEquals(expected, result)
        }
    }

    @Test
    fun `test invoke returns successful result with empty list of user queries`() = runTest {
        val expected = listOf<Query>()
        coEvery {
            queriesRepository.fetchAllUserQueries()
        } returns flowOf(expected)

        testedClass.invoke().collect { result ->
            assertEquals(expected, result)
        }
    }

    @Test
    fun `test invoke returns db exception`() = runTest {
        val dummyException = RuntimeException("Database Error")
        coEvery {
            queriesRepository.fetchAllUserQueries()
        } throws dummyException

        assertThrows(RuntimeException::class.java) {
            runBlocking {
                testedClass.invoke().collect { result ->
                    assertTrue(result is RuntimeException)
                    assertEquals(result.message, dummyException.message)
                }
            }
        }
    }
}
