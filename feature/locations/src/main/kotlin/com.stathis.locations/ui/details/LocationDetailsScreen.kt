package com.stathis.locations.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.locations.provider.PREVIEW_LOCATION
import com.stathis.locations.provider.PREVIEW_RESIDENTS
import com.stathis.locations.ui.details.components.displayBasicLocationInfo
import com.stathis.locations.ui.details.components.displayCharacters
import com.stathis.ui.loading.LoadingScreen
import com.stathis.ui.topbars.TopBarWithBackNavIcon
import com.stathis.util.util.Callback
import com.stathis.util.util.DimenRes
import com.stathis.util.util.StringRes
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun LocationDetailsScreen(
    locationId: Int,
    viewModel: LocationDetailsViewModel = koinViewModel(parameters = { parametersOf(locationId) }),
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    val state = viewModel.state.collectAsState()

    LocationDetailsContent(
        viewState = state.value,
        onBackNavIconClick = onBackNavIconClick,
        onCharacterClick = onCharacterClick
    )
}

@Composable
private fun LocationDetailsContent(
    viewState: LocationDetailsViewState,
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithBackNavIcon(
                title = stringResource(StringRes.location_details),
                onBackNavigationIconClick = onBackNavIconClick
            )
        },
        content = { paddingValues ->
            when (viewState) {
                is LocationDetailsViewState.Loading -> {
                    LoadingScreen(paddingValues = paddingValues)
                }

                is LocationDetailsViewState.Content -> {
                    Content(
                        paddingValues = paddingValues,
                        data = viewState,
                        onCharacterClick = onCharacterClick
                    )
                }

                is LocationDetailsViewState.Error -> {
                    //FIXME: Will be enabled later on
//                    ErrorScreen(
//                        paddingValues = paddingValues,
//                        title = uiState.errorTitle,
//                        description = uiState.errorDescription
//                    )
                }
            }
        }
    )
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    data: LocationDetailsViewState.Content,
    onCharacterClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(all = dimensionResource(DimenRes.dimen_10))
    ) {
        displayBasicLocationInfo(data.location)
        displayCharacters(
            characters = data.residents,
            onCharacterClick = onCharacterClick
        )
    }
}

@Preview
@Composable
private fun ContentPreview() {
    Content(
        paddingValues = PaddingValues(all = dimensionResource(DimenRes.dimen_8)),
        data = LocationDetailsViewState.Content(
            location = PREVIEW_LOCATION,
            residents = PREVIEW_RESIDENTS
        ),
        onCharacterClick = {}
    )
}
