package com.stathis.database.queries

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.stathis.database.db.queries.QueriesDao
import com.stathis.database.db.queries.QueriesLocalDatabase
import com.stathis.database.db.queries.QueryEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class QueriesDaoTest {

    private lateinit var queriesDb: QueriesLocalDatabase
    private lateinit var dao: QueriesDao

    private val dispatcher: CoroutineDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        queriesDb = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext<Context>(),
            QueriesLocalDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = queriesDb.dao()
    }

    @After
    fun close() = queriesDb.close()

    companion object {

        private val QUERY: QueryEntity = QueryEntity(id = 1, name = "My query")
    }

    /**
     * Tests getAllQueries() dao method.
     *
     * Inserts a query to the database and checks that it has been successfully inserted to db.
     */

    @Test
    fun given_db_is_not_empty_when_calling_getAllQueries_method_then_return_all_entities() = runTest(dispatcher) {
        val result: Long = dao.insert(QUERY)
        assertEquals(result, QUERY.id.toLong())

        val entities: List<QueryEntity>? = dao.getAllQueries().firstOrNull()
        assertTrue(entities != null && entities.isNotEmpty())

        assertEquals(entities?.first(), QUERY)
    }

    /**
     * Tests getAllQueries() dao method with DESC order.
     *
     * Inserts a query to the database and checks that it has been successfully inserted to db.
     */

    @Test
    fun given_db_is_not_empty_when_calling_getAllQueries_method_then_return_all_entities_order_by_DESC_order() =
        runTest(dispatcher) {
            val queries: List<QueryEntity> = listOf(
                QueryEntity(id = 2, name = "Rick"),
                QueryEntity(id = 1, name = "Morty")
            )

            queries.forEach { query ->
                val result: Long = dao.insert(query)
                assertEquals(result, query.id.toLong())
            }

            val sortedQueries = queries.sortedByDescending { entity -> entity.id }
            val entities: List<QueryEntity>? = dao.getAllQueries().firstOrNull()

            assertTrue(entities != null && entities.isNotEmpty())
            assertEquals(sortedQueries, entities)
        }

    /**
     * Tests getQueryByName(name: String) dao method.
     *
     * Inserts a query to the database, ensures that it has been successfully inserted and then fetches that by name.
     */

    @Test
    fun given_db_is_not_empty_when_calling_getQueryByName_method_then_return_the_query_that_matches_the_given_name() =
        runTest(dispatcher) {
            val result: Long = dao.insert(QUERY)
            assertEquals(result, QUERY.id.toLong())

            val entity: QueryEntity? = dao.getQueryByName(QUERY.name).firstOrNull()?.first()
            assertTrue(entity != null)

            assertEquals(entity, QUERY)
        }

    /**
     * Tests insert(entity : QueryEntity) dao method.
     *
     * Inserts a query to the database, ensures that it has been successfully inserted.
     * Then fetches the total db results & ensures the element exists in db.
     */

    @Test
    fun given_character_entity_when_calling_insert_method_then_verify_that_the_item_has_been_inserted_to_db() =
        runTest(dispatcher) {
            val result: Long = dao.insert(QUERY)
            assertEquals(result, QUERY.id.toLong())

            val entities: List<QueryEntity>? = dao.getAllQueries().firstOrNull()
            assertTrue(entities != null && entities.isNotEmpty())

            assertEquals(entities?.first(), QUERY)
        }

    /**
     * Tests delete(entity : QueryEntity) dao method.
     *
     * Inserts a query to the database, ensures that it has been successfully inserted
     * and then deletes the elements & ensures that it has been deleted successfully.
     */

    @Test
    fun given_db_is_not_empty_when_calling_delete_method_then_verify_that_the_item_has_been_deleted_from_db() =
        runTest(dispatcher) {
            val queries: List<QueryEntity> = listOf(
                QueryEntity(id = 2, name = "Rick"),
                QueryEntity(id = 1, name = "Morty")
            )

            queries.forEach { query ->
                val result: Long = dao.insert(query)
                assertEquals(result, query.id.toLong())
            }

            queries.forEach { query -> dao.delete(query) }

            val isDbEmpty: Boolean = dao.getAllQueries().firstOrNull()?.isEmpty() == true
            assertTrue(isDbEmpty)
        }
}
