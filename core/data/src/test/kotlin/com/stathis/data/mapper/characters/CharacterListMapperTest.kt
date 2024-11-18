package com.stathis.data.mapper.characters

import com.stathis.model.characters.CharacterResponse
import com.stathis.network.model.characters.CharacterResponseDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CharacterListMapperTest {

    @Test
    fun `given a list of dto, when calling toDomainModel, return list of domain models`() {
        val dtoModelList: List<CharacterResponseDto> = listOf(
            CharacterResponseDto(
                id = 1,
                name = "Rick"
            ),
            CharacterResponseDto(
                id = 2,
                name = "Morty"
            )
        )

        val result: List<CharacterResponse> = CharacterListMapper.toDomainModel(dtoModelList)

        dtoModelList.zip(result).forEach { (dto, domainModel) ->
            assertEquals(dto.id, domainModel.id)
            assertEquals(dto.name, domainModel.name)
        }
    }

    @Test
    fun `given a null dto list as input, when calling toDomainModel, return an empty list`() {
        val dtoModelList: List<CharacterResponseDto>? = null
        val result: List<CharacterResponse> = CharacterListMapper.toDomainModel(dtoModelList)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `given an empty dto list as input, when calling toDomainModel, return an empty list`() {
        val dtoModelList: List<CharacterResponseDto>? = listOf()
        val result: List<CharacterResponse> = CharacterListMapper.toDomainModel(dtoModelList)
        assertTrue(result.isEmpty())
    }
}
