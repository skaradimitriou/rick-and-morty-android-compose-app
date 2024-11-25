package com.stathis.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.stathis.characters.navigation.characterRoute
import com.stathis.common.navigation.CharactersRoute
import com.stathis.common.navigation.EpisodesRoute
import com.stathis.episodes.navigation.episodesRoute

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CharactersRoute.Home
    ) {
        characterRoute(
            navController = navController,
            onEpisodeClick = { episodeId ->
                navController.navigate(EpisodesRoute.Details(episodeId))
            }
        )

        episodesRoute(
            navController = navController,
            onCharacterClick = { charactedId ->
                navController.navigate(CharactersRoute.Details(charactedId))
            }
        )
    }
}
