package com.stathis.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.stathis.characters.navigation.HomeRoute
import com.stathis.characters.navigation.characterRoute
import com.stathis.characters.navigation.model.CharacterDetailsArgs
import com.stathis.episodes.navigation.episodesRoute
import com.stathis.episodes.navigation.model.EpisodeDetailsArgs

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        characterRoute(
            navController = navController,
            onEpisodeClick = { episodeId ->
                navController.navigate(EpisodeDetailsArgs(episodeId))
            }
        )

        episodesRoute(
            navController = navController,
            onCharacterClick = { charactedId ->
                navController.navigate(CharacterDetailsArgs(charactedId))
            }
        )
    }
}
