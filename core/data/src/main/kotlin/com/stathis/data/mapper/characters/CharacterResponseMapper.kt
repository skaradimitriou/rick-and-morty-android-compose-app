package com.stathis.data.mapper.characters

import com.stathis.common.util.toNotNull
import com.stathis.data.mapper.BaseMapper
import com.stathis.model.characters.CharacterResponse
import com.stathis.network.model.characters.CharacterResponseDto

object CharacterResponseMapper : BaseMapper<CharacterResponseDto?, CharacterResponse> {

    override fun toDomainModel(dto: CharacterResponseDto?) = CharacterResponse(
        id = dto?.id.toNotNull(),
        name = dto?.name.toNotNull(),
        status = dto?.status.toNotNull(),
        species = dto?.species.toNotNull(),
        type = dto?.type.toNotNull(),
        gender = dto?.gender.toNotNull(),
        origin = dto?.origin?.name.toNotNull(),
        location = dto?.location?.name.toNotNull(),
        image = dto?.image.toNotNull(),
        episode = dto?.episode.toDomainEpisodeIds(),
        url = dto?.url.toNotNull(),
        created = dto?.created.toNotNull()
    )

    private fun List<String>?.toDomainEpisodeIds() = this?.map {
        it.substringAfterLast("/")
    } ?: listOf()
}