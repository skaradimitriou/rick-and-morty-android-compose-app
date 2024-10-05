package com.stathis.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.stathis.details.navigation.DetailsScreen
import com.stathis.details.navigation.detailsScreenRoute
import com.stathis.home.navigation.HomeRoute
import com.stathis.home.navigation.homeScreenRoute

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        homeScreenRoute(
            onCharacterClick = { characterId ->
                navController.navigate(DetailsScreen(characterId))
            }
        )

        detailsScreenRoute(
            onBackNavIconClick = {
                navController.navigateUp()
            }
        )
    }
}
