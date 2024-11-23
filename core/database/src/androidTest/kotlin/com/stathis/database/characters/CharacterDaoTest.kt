package com.stathis.database.characters

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.stathis.database.db.characters.CharacterEntity
import com.stathis.database.db.characters.CharactersDao
import com.stathis.database.db.characters.CharactersLocalDatabase
import com.stathis.model.characters.CharacterStatus
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharacterDaoTest {

    private lateinit var characterDb: CharactersLocalDatabase
    private lateinit var dao: CharactersDao

    private val dispatcher: CoroutineDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        characterDb = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext<Context>(),
            CharactersLocalDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = characterDb.dao()
    }

    @After
    fun close() = characterDb.close()

    companion object {

        private val DUMMY_ENTITY: CharacterEntity = CharacterEntity(
            id = 0,
            name = "Character Name",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "Type",
            gender = "Male",
            origin = "Earth",
            location = "Earth",
            image = "www.image.com/path",
            episode = listOf(),
            url = "www.domain.com",
            created = "XX-XX-2024"
        )
    }

    /**
     * Tests that getAllCharacters() Character Dao method that works as expected.
     *
     * - Inserts a List<CharacterEntity to db and upon insertion asserts that:
     *   a. The size of the given character list is equal to the entities list retrieved from db
     *   b. The given character list has the same elements as the entities list retrieved from db
     */

    @Test
    fun given_list_of_CharacterEntity_when_insertingAll_to_db_then_verify_that_elements_has_been_added_successfully() =
        runTest(dispatcher) {
            val characters: List<CharacterEntity> = listOf(
                DUMMY_ENTITY.copy(id = 1, name = "Rick"),
                DUMMY_ENTITY.copy(id = 2, name = "Morty")
            )

            val insertedItemsIds: LongArray = dao.insertAll(characters)
            assertEquals(insertedItemsIds.toList(), characters.map { it.id.toLong() })

            val entities = dao.getAllCharacters().firstOrNull()

            assertEquals(characters.size, entities?.size)
            assertEquals(characters, entities)
        }

    /**
     * Tests that getCharacterById(id: Int) Character Dao method that works as expected.
     *
     * - Queries a CharacterEntity by id and upon result asserts that the character exists in db
     *   and its the same as the given character
     */

    @Test
    fun given_character_id_when_calling_getCharacterById_then_return_the_queried_item() = runTest(dispatcher) {
        val character: CharacterEntity = DUMMY_ENTITY.copy(id = 1, name = "Rick")

        val insertedItemId: Long = dao.insert(character)
        assertEquals(insertedItemId, character.id.toLong())

        val entity = dao.getCharacterById(character.id).firstOrNull()
        assertEquals(character, entity)
    }

    /**
     * Tests that insert(entity: CharacterEntity) Character Dao method that works as expected.
     *
     * - Inserts a single CharacterEntity to db and upon insertion asserts that the character exists in db.
     */

    @Test
    fun given_CharacterEntity_when_inserting_to_db_then_verify_that_the_item_has_been_added_successfully() =
        runTest(dispatcher) {
            val character: CharacterEntity = DUMMY_ENTITY.copy(id = 1, name = "Rick")

            val insertedItemId: Long = dao.insert(character)
            assertEquals(insertedItemId, character.id.toLong())

            val entity = dao.getAllCharacters().firstOrNull()?.find { it.id == character.id }
            assertEquals(character, entity)
        }

    /**
     * Tests that insertAll(list: List<CharacterEntity>) Character Dao method that works as expected.
     *
     * - Inserts multiple CharacterEntity to db and upon insertion asserts that the characters exists in db.
     */

    @Test
    fun given_list_of_CharacterEntity_when_inserting_to_db_then_verify_that_elements_has_been_added() = runTest(dispatcher) {
        val characters: List<CharacterEntity> = listOf(
            DUMMY_ENTITY.copy(id = 1, name = "Rick"),
            DUMMY_ENTITY.copy(id = 2, name = "Morty")
        )

        val insertedItemIds: LongArray = dao.insertAll(characters)
        assertEquals(insertedItemIds.toList(), characters.map { it.id.toLong() })

        val entities = dao.getAllCharacters().firstOrNull()

        assertEquals(characters.size, entities?.size)
        assertEquals(characters, entities)
    }

    /**
     * Tests that deleteAll() Character Dao method that works as expected.
     *
     * - Inserts multiple CharacterEntity to db and upon insertion deletes them. Then assert that db is empty
     */

    @Test
    fun given_list_of_CharacterEntity_when_calling_deleteAll_method_then_verify_that_db_is_empty() = runTest(dispatcher) {
        val characters: List<CharacterEntity> = listOf(
            DUMMY_ENTITY.copy(id = 1, name = "Rick"),
            DUMMY_ENTITY.copy(id = 2, name = "Morty")
        )

        val insertedItemIds: LongArray = dao.insertAll(characters)
        assertEquals(insertedItemIds.toList(), characters.map { it.id.toLong() })

        val dbIsNotEmpty: Boolean = dao.getAllCharacters().first().isNotEmpty()
        assertTrue(dbIsNotEmpty)

        dao.deleteAll()

        val dbIsEmpty: Boolean = dao.getAllCharacters().first().isEmpty()
        assertTrue(dbIsEmpty)
    }

}
