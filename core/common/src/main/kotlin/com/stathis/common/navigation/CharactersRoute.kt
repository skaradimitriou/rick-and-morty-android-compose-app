package com.stathis.common.navigation

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

/**
 * Represents the [Screen] that are a part of the Characters flow
 */

sealed class CharactersRoute : Screen {

    /**
     * Represents the home screen of the characters flow.
     */
    @Serializable
    object Home : CharactersRoute()

    /**
     * Represents the details screen of the characters flow.
     * @param characterId: the character's unique id.
     */
    @SuppressLint("UnsafeOptInUsageError")
    @Serializable
    data class Details(val characterId: Int) : CharactersRoute()

    /**
     * Represents the search screen of the characters flow.
     */
    @Serializable
    object Search : CharactersRoute()
}
