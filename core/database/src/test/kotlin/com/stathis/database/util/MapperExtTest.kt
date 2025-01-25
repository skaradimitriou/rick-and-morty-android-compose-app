package com.stathis.database.util

import com.stathis.database.db.characters.CharacterEntity
import com.stathis.database.db.queries.QueryEntity
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus
import com.stathis.model.search.Query
import com.stathis.testing.DUMMY_CHARACTER
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperExtTest {

    @Test
    fun `given CharacterResponse, when calling toEntity() method, then return CharacterEntity`() {
        val resultAfterMapping: CharacterEntity = DUMMY_CHARACTER.toEntity()
        val expected = CharacterEntity(
            id = 1,
            name = "Rick Sanchez",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "XX",
            gender = "Male",
            origin = "{\"name\":\"Earth (C-137)\",\"id\":1}",
            location = "{\"name\":\"Earth (Replacement Dimension)\",\"id\":2}",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf("1", "2"),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )
        assertEquals(expected, resultAfterMapping)
    }

    @Test
    fun `given CharacterEntity, when calling toEntity() method, then return CharacterResponse`() {
        val dummyEntity = CharacterEntity(
            id = 1,
            name = "Rick Sanchez",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "XX",
            gender = "Male",
            origin = "{\"name\":\"Earth (C-137)\",\"id\":1}",
            location = "{\"name\":\"Earth (Replacement Dimension)\",\"id\":2}",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf("1", "2"),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )

        val resultAfterMapping: CharacterResponse = dummyEntity.toCharacter()
        assertEquals(DUMMY_CHARACTER, resultAfterMapping)
    }

    @Test
    fun `given Query, when calling toEntity() method, then return QueryEntity`() {
        val query = Query(name = "My query")
        val entity: QueryEntity = query.toEntity()
        assertEquals(query.name, entity.name)
    }

    @Test
    fun `given QueryEntity, when calling toEntity() method, then return Query`() {
        val entity = QueryEntity(name = "My query")
        val query: Query = entity.toQuery()
        assertEquals(query.name, entity.name)
    }
}
