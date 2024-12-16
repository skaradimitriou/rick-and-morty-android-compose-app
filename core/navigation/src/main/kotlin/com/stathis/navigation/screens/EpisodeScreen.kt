package com.stathis.navigation.screens

import android.annotation.SuppressLint

/**
 * Represents the [Screen] that are a part of the Episodes flow
 */

sealed class EpisodeScreen : Screen {

    @SuppressLint("UnsafeOptInUsageError")
    @kotlinx.serialization.Serializable
    data class Details(val episodeInt: Int) : EpisodeScreen()
}
