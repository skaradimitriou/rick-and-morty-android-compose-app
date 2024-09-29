package com.stathis.data.mapper.characters

import com.stathis.data.mapper.BaseMapper
import com.stathis.data.mapper.common.PaginationMapper
import com.stathis.model.characters.CharacterWrapper
import com.stathis.network.model.characters.CharacterResponseDto
import com.stathis.network.model.characters.CharacterWrapperDto

object CharacterMapper : BaseMapper<CharacterWrapperDto?, CharacterWrapper> {

    override fun toDomainModel(dto: CharacterWrapperDto?): CharacterWrapper = CharacterWrapper(
        info = PaginationMapper.toDomainModel(dto?.info),
        results = dto?.results.toDomainCharacters()
    )

    private fun List<CharacterResponseDto>?.toDomainCharacters() = this?.map {
        it.toDomainModel()
    } ?: listOf()

    private fun CharacterResponseDto?.toDomainModel() = CharacterResponseMapper.toDomainModel(this)
}