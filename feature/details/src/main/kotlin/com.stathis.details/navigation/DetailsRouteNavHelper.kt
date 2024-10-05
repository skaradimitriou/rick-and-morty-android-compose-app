package com.stathis.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.stathis.common.util.Callback
import com.stathis.details.ui.DetailsScreen
import kotlinx.serialization.Serializable

@Serializable
data class DetailsScreen(
    val characterId: Int
)

fun NavGraphBuilder.detailsScreenRoute(
    onBackNavIconClick: Callback
) {
    composable<DetailsScreen> {
        val args = it.toRoute<DetailsScreen>()
        DetailsScreen(
            characterId = args.characterId,
            onBackNavIconClick = onBackNavIconClick
        )
    }
}
