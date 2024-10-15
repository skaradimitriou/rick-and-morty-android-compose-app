package com.stathis.testing

import com.stathis.model.episodes.Episode

object EpisodeFakes {

    fun provideEmptyEpisode(): Episode = Episode(
        id = 0,
        name = "",
        airDate = "",
        episode = "",
        characters = listOf(),
        url = "",
        created = ""
    )

    fun provideDummyEpisode(): Episode = Episode(
        id = 123,
        name = "Some episode name",
        airDate = "XX-XX-2024",
        episode = "#123",
        characters = listOf(),
        url = "https://www.myepisode.com/123",
        created = "XX-XX-2024"
    )

    fun provideDummyEpisodeList(): List<Episode> = listOf(

    )
}
