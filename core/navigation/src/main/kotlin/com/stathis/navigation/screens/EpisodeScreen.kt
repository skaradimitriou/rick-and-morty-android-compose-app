package com.stathis.navigation.screens

import kotlinx.serialization.Serializable

/**
 * Represents the [Screen] that are a part of the Episodes flow
 */

sealed class EpisodeScreen : Screen {

    @Serializable
    data class Details(val episodeInt: Int) : EpisodeScreen()
}
