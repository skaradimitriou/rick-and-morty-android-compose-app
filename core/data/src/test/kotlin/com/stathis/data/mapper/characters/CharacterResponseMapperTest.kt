package com.stathis.data.mapper.characters

import com.stathis.model.characters.CharacterResponse
import com.stathis.network.model.characters.CharacterResponseDto
import org.junit.Test
import kotlin.test.assertEquals

class CharacterResponseMapperTest {

    @Test
    fun `given null CharacterResponseDto model, when calling toDomainModel, return non null CharacterResponse domain model`() {
        val dtoModel: CharacterResponseDto? = null
        val domainModel: CharacterResponse = CharacterResponseMapper.toDomainModel(dtoModel)
        assertEquals(domainModel.id, 0)
        assertEquals(domainModel.name, "")
    }

    @Test
    fun `given a CharacterResponseDto model, when calling toDomainModel, return non null CharacterResponse domain model`() {
        val dtoModel: CharacterResponseDto = CharacterResponseDto(
            id = 1,
            name = "Rick"
        )

        val domainModel: CharacterResponse = CharacterResponseMapper.toDomainModel(dtoModel)
        assertEquals(dtoModel.id, domainModel.id)
        assertEquals(dtoModel.name, domainModel.name)
    }
}
