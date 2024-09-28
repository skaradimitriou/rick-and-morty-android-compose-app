package com.stathis.rickandmortyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.stathis.home.navigation.HomeRoute
import com.stathis.home.navigation.homeScreenRoute
import com.stathis.details.navigation.DetailsRoute
import com.stathis.details.navigation.detailsScreenRoute

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        homeScreenRoute(
            onCharacterClick = {
                navController.navigate(DetailsRoute)
            }
        )

        detailsScreenRoute(
            onBackNavIconClick = {
                navController.popBackStack()
            }
        )
    }
}