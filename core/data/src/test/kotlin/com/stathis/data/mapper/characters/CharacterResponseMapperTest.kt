package com.stathis.data.mapper.characters

import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus
import com.stathis.network.model.characters.CharacterInformationDto
import com.stathis.network.model.characters.CharacterResponseDto
import org.junit.Test
import kotlin.test.assertEquals

class CharacterResponseMapperTest {

    @Test
    fun `mapping of CharacterResponseDto to empty CharacterResponse model on null input`() {
        val givenDtoModel: CharacterResponseDto? = null
        val domainModel = CharacterResponseMapper.toDomainModel(givenDtoModel)

        val expected = CharacterResponse(
            id = 0,
            name = "",
            status = CharacterStatus.UNKNOWN,
            species = "",
            type = "",
            gender = "",
            origin = "",
            location = "",
            image = "",
            episode = listOf(),
            url = "",
            created = ""
        )

        assertEquals(domainModel, expected)
    }

    @Test
    fun `mapping of CharacterResponseDto to CharacterResponse model on non-null response`() {
        val givenDtoModel = CharacterResponseDto(
            id = 123,
            name = "Character Name",
            status = "Alive",
            species = "Human",
            type = "Type",
            gender = "Male",
            origin = CharacterInformationDto(
                name = "Somewhere",
                url = "some url"
            ),
            location = CharacterInformationDto(
                name = "Earth",
                url = "some url"
            ),
            image = "",
            episode = listOf("8"),
            url = "",
            created = ""
        )

        val domainModel = CharacterResponseMapper.toDomainModel(givenDtoModel)

        val expected = CharacterResponse(
            id = 123,
            name = "Character Name",
            status = CharacterStatus.ALIVE,
            species = "Human",
            type = "Type",
            gender = "Male",
            origin = "Somewhere",
            location = "Earth",
            image = "",
            episode = listOf("8"),
            url = "",
            created = ""
        )

        assertEquals(domainModel, expected)
    }
}
