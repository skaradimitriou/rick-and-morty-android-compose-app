package com.stathis.navigation.screens

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

/**
 * Represents the [Screen] that are a part of the Characters flow
 */

sealed class CharacterScreen : Screen {

    /**
     * Represents the home screen of the characters flow.
     */
    @Serializable
    object Home : CharacterScreen()

    /**
     * Represents the details screen of the characters flow.
     * @param characterId: the character's unique id.
     */
    @SuppressLint("UnsafeOptInUsageError")
    @Serializable
    data class Details(val characterId: Int) : CharacterScreen()

    /**
     * Represents the search screen of the characters flow.
     */
    @Serializable
    object Search : CharacterScreen()
}
