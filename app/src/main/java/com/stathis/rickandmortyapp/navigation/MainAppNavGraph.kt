package com.stathis.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.stathis.characters.navigation.characterNavGraph
import com.stathis.episodes.navigation.episodesNavGraph
import com.stathis.locations.navigation.locationsNavGraph
import com.stathis.navigation.screens.CharacterScreen
import com.stathis.navigation.screens.EpisodeScreen

@Composable
internal fun MainAppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CharacterScreen.Home
    ) {
        characterNavGraph(
            navController = navController,
            onEpisodeClick = { episodeId ->
                navController.navigate(EpisodeScreen.Details(episodeId))
            }
        )

        episodesNavGraph(
            navController = navController,
            onCharacterClick = { characterId ->
                navController.navigate(CharacterScreen.Details(characterId))
            }
        )

        locationsNavGraph(
            navController = navController,
            onCharacterClick = { characterId ->
                navController.navigate(CharacterScreen.Details(characterId))
            }
        )
    }
}
