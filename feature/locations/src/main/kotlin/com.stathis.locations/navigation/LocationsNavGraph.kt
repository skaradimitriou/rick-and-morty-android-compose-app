package com.stathis.locations.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.common.util.Callback
import com.stathis.locations.ui.details.LocationDetailsScreen
import com.stathis.navigation.screens.LocationScreen

/**
 * Represents the Locations Navigation Graph.
 */

fun NavGraphBuilder.locationsNavGraph(
    navController: NavController,
    onCharacterClick: (Int) -> Unit
) {
    locationDetailsRoute(
        onBackNavIconClick = {
            navController.navigateUp()
        },
        onCharacterClick = onCharacterClick
    )
}

/**
 * Represents the Locations Details Route.
 */

private fun NavGraphBuilder.locationDetailsRoute(
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    composable<LocationScreen.Details> {
        val args = it.toRoute<LocationScreen.Details>()
        LocationDetailsScreen(
            locationId = args.locationId,
            onBackNavIconClick = onBackNavIconClick,
            onCharacterClick = onCharacterClick
        )
    }
}

