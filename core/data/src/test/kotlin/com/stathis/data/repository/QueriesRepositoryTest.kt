package com.stathis.data.repository

import com.stathis.database.db.queries.QueriesLocalDatabase
import com.stathis.database.db.queries.QueryEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class QueriesRepositoryTest {

    private val localQueriesDb: QueriesLocalDatabase = mockk(relaxed = true)
    private lateinit var repository: QueriesRepositoryImpl

    private val dispatcher: TestDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        repository = QueriesRepositoryImpl(localDataSource = localQueriesDb)
    }

    @Test
    fun `given queries db has entries, when calling fetchAllUserQueries, then return mapped list of domain model`() =
        runTest(dispatcher) {
            val entities: List<QueryEntity> = listOf(
                QueryEntity(id = 1, name = "Rick"),
                QueryEntity(id = 1, name = "Morty")
            )

            coEvery { localQueriesDb.dao().getAllQueries() } returns flowOf(entities)

            repository.fetchAllUserQueries().collect { result ->
                coVerify { localQueriesDb.dao().getAllQueries() }

                assertTrue(result.isNotEmpty())
                assertEquals(result.size, entities.size)

                entities.zip(result) { entity, query ->
                    assertEquals(entity.name, query.name)
                }
            }
        }

    @Test
    fun `given queries db has no entries, when calling fetchAllUserQueries, then return empty mapped list of domain model`() =
        runTest(dispatcher) {
            val entities: List<QueryEntity> = listOf()
            coEvery { localQueriesDb.dao().getAllQueries() } returns flowOf(entities)

            repository.fetchAllUserQueries().collect { result ->
                coVerify { localQueriesDb.dao().getAllQueries() }
                assertTrue(result.isEmpty())
            }
        }

    @Test
    fun `given query does not exist in db, when calling insertNewUserQuery method, then insert to db`() =
        runTest(dispatcher) {
            val queryName: String = "My new query"
            coEvery { localQueriesDb.dao().getQueryByName(queryName) } returns flowOf(listOf())

            repository.insertNewUserQuery(queryName)

            localQueriesDb.dao().getAllQueries().collect { entities ->
                val query = entities.firstOrNull { it.name == queryName }
                assertTrue(query != null)
                assertEquals(queryName, query.name)
            }
        }
}
