package com.stathis.data.mapper.episodes

import com.stathis.data.mapper.BaseMapper
import com.stathis.model.episodes.Episode
import com.stathis.network.model.episodes.EpisodeWrapperDto

object EpisodesWrapperMapper : BaseMapper<EpisodeWrapperDto?, List<Episode>> {

    override fun toDomainModel(dto: EpisodeWrapperDto?): List<Episode> {
        return EpisodeListMapper.toDomainModel(dto?.results)
    }
}
