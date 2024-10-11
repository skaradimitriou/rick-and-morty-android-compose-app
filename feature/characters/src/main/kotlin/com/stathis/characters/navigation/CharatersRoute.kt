package com.stathis.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.characters.navigation.model.CharacterDetailsArgs
import com.stathis.characters.ui.details.DetailsScreen
import com.stathis.characters.ui.home.HomeScreen
import com.stathis.common.util.Callback

fun NavGraphBuilder.characterRoute(
    navController: NavController,
    onEpisodeClick: (Int) -> Unit
) {
    homeRoute { characterId ->
        navController.navigate(CharacterDetailsArgs(characterId))
    }

    detailsScreenRoute(
        onBackNavIconClick = {
            navController.navigateUp()
        },
        onEpisodeClick = onEpisodeClick
    )
}

const val HomeRoute = "homeRoute"
internal fun NavGraphBuilder.homeRoute(onCharacterClick: (Int) -> Unit) {
    composable(HomeRoute) {
        HomeScreen(
            onCharacterClick = onCharacterClick
        )
    }
}

internal fun NavGraphBuilder.detailsScreenRoute(
    onBackNavIconClick: Callback,
    onEpisodeClick: (Int) -> Unit
) {
    composable<CharacterDetailsArgs> {
        val args = it.toRoute<CharacterDetailsArgs>()
        DetailsScreen(
            characterId = args.characterId,
            onBackNavIconClick = onBackNavIconClick,
            onEpisodeClick = onEpisodeClick
        )
    }
}
