package com.stathis.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.characters.navigation.model.CharacterDetailsArgs
import com.stathis.characters.ui.details.DetailsScreen
import com.stathis.characters.ui.home.HomeScreen
import com.stathis.characters.ui.search.SearchScreen
import com.stathis.common.util.Callback

fun NavGraphBuilder.characterRoute(
    navController: NavController,
    onEpisodeClick: (Int) -> Unit
) {
    homeRoute(
        onCharacterClick = { characterId -> navController.navigate(CharacterDetailsArgs(characterId)) },
        onSearchIconClick = { navController.navigate(SearchRoute) }
    )

    searchRoute(
        onBackNavIconClick = { navController.navigateUp() },
        onCharacterClick = { characterId -> navController.navigate(CharacterDetailsArgs(characterId)) },
        onEpisodeClick = onEpisodeClick
    )

    detailsScreenRoute(
        onBackNavIconClick = { navController.navigateUp() },
        onEpisodeClick = onEpisodeClick
    )
}

const val HomeRoute = "homeRoute"
internal fun NavGraphBuilder.homeRoute(
    onCharacterClick: (Int) -> Unit,
    onSearchIconClick: Callback
) {
    composable(HomeRoute) {
        HomeScreen(
            onCharacterClick = onCharacterClick,
            onSearchIconClick = onSearchIconClick
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

const val SearchRoute = "searchRoute"
internal fun NavGraphBuilder.searchRoute(
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
) {
    composable(SearchRoute) {
        SearchScreen(
            onBackNavIconClick = onBackNavIconClick,
            onCharacterClick = onCharacterClick,
            onEpisodeClick = onEpisodeClick
        )
    }
}
