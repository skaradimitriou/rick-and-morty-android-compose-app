package com.stathis.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import com.stathis.characters.navigation.characterNavGraph
import com.stathis.episodes.navigation.episodesNavGraph
import com.stathis.navigation.screens.CharacterScreen
import com.stathis.navigation.screens.EpisodeScreen

@Composable
fun MainAppNavGraph() {
    val navController = androidx.navigation.compose.rememberNavController()

    androidx.navigation.compose.NavHost(
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
    }
}
