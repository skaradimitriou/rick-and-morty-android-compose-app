package com.stathis.data.mapper.characters

import com.stathis.common.util.toListOf
import com.stathis.model.characters.CharacterWrapper
import com.stathis.model.common.PaginationInfo
import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto
import com.stathis.network.model.common.PaginationInfoDto
import org.junit.Test
import kotlin.test.assertEquals

class CharacterMapperTest {

    @Test
    fun `given null dto model, when calling toDomainModel, return non null domain model`() {
        val dtoModel: CharacterWrapperDto? = null
        val domainModel = CharacterMapper.toDomainModel(dtoModel)

        val expected = CharacterWrapper(
            info = PaginationInfo(0, 0, "", 0),
            results = listOf()
        )

        assertEquals(domainModel, expected)
    }

    @Test
    fun `given dto model, when calling toDomainModel, return mapped domain model`() {
        val dtoModel: CharacterWrapperDto = CharacterWrapperDto(
            info = PaginationInfoDto(0, 0, "", 0),
            results = listOf(
                CharacterResponseDto(
                    id = 1,
                    name = "Rick"
                ),
                CharacterResponseDto(
                    id = 2,
                    name = "Morty"
                )
            )
        )
        val domainModel: CharacterWrapper = CharacterMapper.toDomainModel(dtoModel)

        assertEquals(dtoModel.info?.count, domainModel.info.count)

        dtoModel.results.toListOf { it }.zip(domainModel.results) { dtoModel, domainModel ->
            assertEquals(dtoModel.id, domainModel.id)
            assertEquals(dtoModel.name, domainModel.name)
        }
    }
}
