package com.stathis.database.characters

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.stathis.model.characters.CharacterStatus
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
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
        val context = ApplicationProvider.getApplicationContext<Context>()
        characterDb = Room.inMemoryDatabaseBuilder(
            context = context,
            CharactersLocalDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = characterDb.dao()
    }

    @After
    fun close() = characterDb.close()

    @Test
    fun given_list_of_CharacterEntity_when_inserting_to_db_then_verify_that_elements_has_been_added() = runTest(dispatcher) {
        val entities: List<CharacterEntity> = listOf(
            CharacterEntity(
                id = 1,
                name = "Rick",
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
        )

        dao.insertAll(entities)

        val results = dao.getAllCharacters().firstOrNull()

        assertEquals(results?.size, entities.size)
        assertEquals(results?.firstOrNull(), entities.firstOrNull())
    }

    @Test
    fun given_CharacterEntity_when_inserting_to_db_then_verify_that_element_has_been_added_by_id() = runTest(dispatcher) {
        val entity = CharacterEntity(
            id = 1,
            name = "Rick",
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

        dao.insert(entity)

        val character = dao.getCharacterById(1).firstOrNull()
        assertEquals(character, entity)
    }
}
