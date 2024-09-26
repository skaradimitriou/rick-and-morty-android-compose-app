package com.stathis.data.mapper.characters

import com.stathis.common.ext.toNotNull
import com.stathis.data.mapper.BaseMapper
import com.stathis.data.mapper.common.PaginationMapper
import com.stathis.model.characters.CharacterResponse
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

    private fun CharacterResponseDto?.toDomainModel() = CharacterResponse(
        id = this?.id.toNotNull(),
        name = this?.name.toNotNull(),
        status = this?.status.toNotNull(),
        species = this?.species.toNotNull(),
        type = this?.type.toNotNull(),
        gender = this?.gender.toNotNull(),
        image = this?.image.toNotNull(),
        url = this?.url.toNotNull(),
        created = this?.created.toNotNull()
    )
}