package com.stathis.data.mapper.characters

import com.stathis.model.characters.CharacterWrapper
import com.stathis.model.common.PaginationInfo
import com.stathis.network.model.characters.CharacterInformationDto
import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto
import com.stathis.network.model.common.PaginationInfoDto
import com.stathis.testing.CharactersFakes
import org.junit.Test
import kotlin.test.assertEquals

class CharacterMapperTest {

    @Test
    fun `mapping of CharacterWrapperDto to empty CharacterWrapper model on null input`() {
        val givenDtoModel: CharacterWrapperDto? = null
        val domainModel = CharacterMapper.toDomainModel(givenDtoModel)

        val expected = CharacterWrapper(
            info = PaginationInfo(0, 0, "", 0),
            results = listOf()
        )

        assertEquals(domainModel, expected)
    }

    @Test
    fun `mapping of CharacterWrapperDto to CharacterWrapper model on response`() {
        val givenDtoModel = CharacterWrapperDto(
            info = PaginationInfoDto(0, 0, "", 0),
            results = listOf(
                CharacterResponseDto(
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
            )
        )
        val domainModel = CharacterMapper.toDomainModel(givenDtoModel)

        val expected = CharacterWrapper(
            info = PaginationInfo(0, 0, "", 0),
            results = listOf(CharactersFakes.provideDummyCharacter())
        )

        assertEquals(domainModel, expected)
    }
}
