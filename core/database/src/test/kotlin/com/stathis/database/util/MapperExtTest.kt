package com.stathis.database.util

import com.stathis.database.db.characters.CharacterEntity
import com.stathis.database.db.queries.QueryEntity
import com.stathis.database.queries.QueryEntity
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus
import com.stathis.model.search.Query
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MapperExtTest {

    @Test
    fun `given CharacterResponse, when calling toEntity() method, then return CharacterEntity`() {
        val character = CharacterResponse(
            id = 1,
            name = "Rick",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "Type",
            gender = "Male",
            origin = "Earth",
            location = "Earth",
            image = "imgPath",
            episode = listOf(),
            url = "",
            created = ""
        )

        val entity: CharacterEntity = character.toEntity()

        assertEquals(character.id, entity.id)
        assertEquals(character.name, entity.name)
        assertEquals(character.status, entity.status)
        assertEquals(character.species, entity.species)
        assertEquals(character.type, entity.type)
        assertEquals(character.gender, entity.gender)
        assertEquals(character.origin, entity.origin)
        assertEquals(character.location, entity.location)
        assertEquals(character.image, entity.image)
        assertEquals(character.episode, entity.episode)
        assertEquals(character.url, entity.url)
        assertEquals(character.created, entity.created)
    }

    @Test
    fun `given CharacterEntity, when calling toEntity() method, then return CharacterResponse`() {
        val entity = CharacterEntity(
            id = 1,
            name = "Rick",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "Type",
            gender = "Male",
            origin = "Earth",
            location = "Earth",
            image = "imgPath",
            episode = listOf(),
            url = "",
            created = ""
        )

        val character: CharacterResponse = entity.toCharacter()

        assertEquals(character.id, entity.id)
        assertEquals(character.name, entity.name)
        assertEquals(character.status, entity.status)
        assertEquals(character.species, entity.species)
        assertEquals(character.type, entity.type)
        assertEquals(character.gender, entity.gender)
        assertEquals(character.origin, entity.origin)
        assertEquals(character.location, entity.location)
        assertEquals(character.image, entity.image)
        assertEquals(character.episode, entity.episode)
        assertEquals(character.url, entity.url)
        assertEquals(character.created, entity.created)
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
