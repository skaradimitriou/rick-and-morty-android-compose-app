package com.stathis.data.mapper.episodes

import com.stathis.common.util.toListOf
import com.stathis.data.mapper.BaseMapper
import com.stathis.model.episodes.Episode
import com.stathis.network.model.episodes.EpisodeDto

internal object EpisodeListMapper : BaseMapper<List<EpisodeDto?>?, List<Episode>> {

    override fun toDomainModel(dto: List<EpisodeDto?>?): List<Episode> {
        return dto.toListOf { EpisodesMapper.toDomainModel(it) }
    }
}
