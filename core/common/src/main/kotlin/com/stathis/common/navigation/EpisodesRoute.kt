package com.stathis.common.navigation

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

/**
 * Represents the [Screen] that are a part of the Episodes flow
 */

sealed class EpisodesRoute : Screen {

    @SuppressLint("UnsafeOptInUsageError")
    @Serializable
    data class Details(val episodeInt: Int) : EpisodesRoute()
}
