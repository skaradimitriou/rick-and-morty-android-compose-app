package com.stathis.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.characters.navigation.model.DetailsScreen
import com.stathis.characters.ui.details.DetailsScreen
import com.stathis.characters.ui.home.HomeScreen
import com.stathis.common.util.Callback

fun NavGraphBuilder.characterRoute(navController: NavController) {
    homeRoute { characterId ->
        navController.navigate(DetailsScreen(characterId))
    }

    detailsScreenRoute {
        navController.navigateUp()
    }
}

const val HomeRoute = "homeRoute"
internal fun NavGraphBuilder.homeRoute(onCharacterClick: (Int) -> Unit) {
    composable(HomeRoute) {
        HomeScreen(
            onCharacterClick = onCharacterClick
        )
    }
}

internal fun NavGraphBuilder.detailsScreenRoute(onBackNavIconClick: Callback) {
    composable<DetailsScreen> {
        val args = it.toRoute<DetailsScreen>()
        DetailsScreen(
            characterId = args.characterId,
            onBackNavIconClick = onBackNavIconClick
        )
    }
}
