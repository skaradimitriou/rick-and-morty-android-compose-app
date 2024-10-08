package com.stathis.data.mapper.episodes

import com.stathis.common.util.toListOf
import com.stathis.common.util.toNotNull
import com.stathis.data.mapper.BaseMapper
import com.stathis.model.episodes.Episode
import com.stathis.network.model.episodes.EpisodeDto

object EpisodesMapper : BaseMapper<EpisodeDto?, Episode> {

    override fun toDomainModel(dto: EpisodeDto?): Episode = Episode(
        id = dto?.id.toNotNull(),
        name = dto?.name.toNotNull(),
        airDate = dto?.air_date.toNotNull(),
        episode = dto?.episode.toNotNull(),
        characters = dto?.characters.toListOf { it },
        url = dto?.url.toNotNull(),
        created = dto?.created.toNotNull()
    )
}
