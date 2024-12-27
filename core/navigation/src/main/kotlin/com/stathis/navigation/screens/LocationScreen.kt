package com.stathis.navigation.screens

import kotlinx.serialization.Serializable

/**
 * Represents the [Screen] that are a part of the Location flow
 */

sealed class LocationScreen : Screen {

    @Serializable
    data class Details(val locationId: Int) : LocationScreen()
}
