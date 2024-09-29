package com.stathis.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stathis.home.ui.HomeScreen

const val HomeRoute = "homeRoute"
fun NavGraphBuilder.homeScreenRoute(
    onCharacterClick: (Int) -> Unit
) {
    composable(HomeRoute) {
        HomeScreen(
            onCharacterClick = onCharacterClick
        )
    }
}