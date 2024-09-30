package com.stathis.network.model.episodes

data class EpisodeDto(
    val id: Int? = null,
    val name: String? = null,
    val air_date: String? = null,
    val episode: String? = null,
    val characters: List<String>? = null,
    val url: String? = null,
    val created: String? = null
)
