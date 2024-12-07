package com.stathis.episodes.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.common.navigation.EpisodesRoute
import com.stathis.common.util.Callback
import com.stathis.episodes.ui.details.EpisodeDetailsScreen

fun NavGraphBuilder.episodesRoute(
    navController: NavController,
    onCharacterClick: (Int) -> Unit
) {
    episodeDetailsRoute(
        onBackNavIconClick = {
            navController.navigateUp()
        },
        onCharacterClick = onCharacterClick
    )
}

private fun NavGraphBuilder.episodeDetailsRoute(
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    composable<EpisodesRoute.Details> {
        val args = it.toRoute<EpisodesRoute.Details>()
        EpisodeDetailsScreen(
            episodeId = args.episodeInt,
            onBackNavIconClick = onBackNavIconClick,
            onCharacterClick = onCharacterClick
        )
    }
}
