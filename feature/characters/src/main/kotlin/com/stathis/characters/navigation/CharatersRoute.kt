package com.stathis.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.characters.ui.details.DetailsScreen
import com.stathis.characters.ui.home.HomeScreen
import com.stathis.characters.ui.search.SearchScreen
import com.stathis.common.navigation.CharactersRoute
import com.stathis.common.util.Callback

fun NavGraphBuilder.characterRoute(
    navController: NavController,
    onEpisodeClick: (Int) -> Unit
) {
    homeRoute(
        onCharacterClick = { characterId ->
            navController.navigate(CharactersRoute.Details(characterId))
        },
        onSearchIconClick = { navController.navigate(CharactersRoute.Search) }
    )

    searchRoute(
        onBackNavIconClick = { navController.navigateUp() },
        onCharacterClick = { characterId ->
            navController.navigate(CharactersRoute.Details(characterId))
        },
        onEpisodeClick = onEpisodeClick
    )

    detailsScreenRoute(
        onBackNavIconClick = { navController.navigateUp() },
        onEpisodeClick = onEpisodeClick
    )
}

internal fun NavGraphBuilder.homeRoute(
    onCharacterClick: (Int) -> Unit,
    onSearchIconClick: Callback
) {
    composable<CharactersRoute.Home> {
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
    composable<CharactersRoute.Details> {
        val args = it.toRoute<CharactersRoute.Details>()
        DetailsScreen(
            characterId = args.characterId,
            onBackNavIconClick = onBackNavIconClick,
            onEpisodeClick = onEpisodeClick
        )
    }
}

internal fun NavGraphBuilder.searchRoute(
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
) {
    composable<CharactersRoute.Search> {
        SearchScreen(
            onBackNavIconClick = onBackNavIconClick,
            onCharacterClick = onCharacterClick,
            onEpisodeClick = onEpisodeClick
        )
    }
}
