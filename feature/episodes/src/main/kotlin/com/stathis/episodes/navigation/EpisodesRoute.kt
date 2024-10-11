package com.stathis.episodes.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.common.util.Callback
import com.stathis.episodes.navigation.model.EpisodeDetailsArgs
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

const val EpisodesRoute = "EpisodesRoute"
internal fun NavGraphBuilder.episodeDetailsRoute(
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    composable<EpisodeDetailsArgs> {
        val args = it.toRoute<EpisodeDetailsArgs>()
        EpisodeDetailsScreen(
            episodeId = args.episodeInt,
            onBackNavIconClick = onBackNavIconClick,
            onCharacterClick = onCharacterClick
        )
    }
}
