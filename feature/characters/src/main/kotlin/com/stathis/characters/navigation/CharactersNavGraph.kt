package com.stathis.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.characters.ui.details.DetailsScreen
import com.stathis.characters.ui.home.HomeScreen
import com.stathis.characters.ui.search.SearchScreen
import com.stathis.common.util.Callback
import com.stathis.navigation.screens.CharacterScreen

fun NavGraphBuilder.characterNavGraph(
    navController: NavController,
    onEpisodeClick: (Int) -> Unit
) {
    homeRoute(
        onCharacterClick = { characterId ->
            navController.navigate(CharacterScreen.Details(characterId))
        },
        onSearchIconClick = { navController.navigate(CharacterScreen.Search) }
    )

    searchRoute(
        onBackNavIconClick = { navController.navigateUp() },
        onCharacterClick = { characterId ->
            navController.navigate(CharacterScreen.Details(characterId))
        },
        onEpisodeClick = onEpisodeClick
    )

    detailsScreenRoute(
        onBackNavIconClick = { navController.navigateUp() },
        onEpisodeClick = onEpisodeClick
    )
}

private fun NavGraphBuilder.homeRoute(
    onCharacterClick: (Int) -> Unit,
    onSearchIconClick: Callback
) {
    composable<CharacterScreen.Home> {
        HomeScreen(
            onCharacterClick = onCharacterClick,
            onSearchIconClick = onSearchIconClick
        )
    }
}

private fun NavGraphBuilder.detailsScreenRoute(
    onBackNavIconClick: Callback,
    onEpisodeClick: (Int) -> Unit
) {
    composable<CharacterScreen.Details> {
        val args = it.toRoute<CharacterScreen.Details>()
        DetailsScreen(
            characterId = args.characterId,
            onBackNavIconClick = onBackNavIconClick,
            onEpisodeClick = onEpisodeClick
        )
    }
}

private fun NavGraphBuilder.searchRoute(
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
) {
    composable<CharacterScreen.Search> {
        SearchScreen(
            onBackNavIconClick = onBackNavIconClick,
            onCharacterClick = onCharacterClick,
            onEpisodeClick = onEpisodeClick
        )
    }
}
