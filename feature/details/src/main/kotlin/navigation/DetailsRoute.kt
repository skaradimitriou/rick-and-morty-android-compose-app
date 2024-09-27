package navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stathis.common.util.Callback
import com.stathis.details.DetailsScreen

const val DetailsRoute = "detailsRoute"
fun NavGraphBuilder.detailsScreenRoute(
    onBackNavIconClick: Callback
) {
    composable(DetailsRoute) {
        DetailsScreen(
            onBackNavIconClick = onBackNavIconClick
        )
    }
}