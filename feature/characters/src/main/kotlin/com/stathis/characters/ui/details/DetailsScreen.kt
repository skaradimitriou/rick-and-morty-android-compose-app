package com.stathis.characters.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.components.displayEpisodeList
import com.stathis.characters.ui.details.components.CharacterDetailsRow
import com.stathis.characters.ui.details.components.CharacterLabel
import com.stathis.characters.ui.details.model.DetailsScreenUiState
import com.stathis.designsystem.components.cards.PosterCardWithLabel
import com.stathis.testing.DUMMY_CHARACTER
import com.stathis.testing.DUMMY_EPISODE
import com.stathis.testing.DUMMY_LOCATION
import com.stathis.ui.error.ErrorScreen
import com.stathis.ui.loading.LoadingScreen
import com.stathis.ui.topbars.TopBarWithBackNavIcon
import com.stathis.util.util.Callback
import com.stathis.util.util.DimenRes
import com.stathis.util.util.StringRes
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DetailsScreen(
    characterId: Int,
    viewModel: DetailsScreenViewModel = koinViewModel(parameters = { parametersOf(characterId) }),
    onBackNavIconClick: Callback,
    onLocationClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailsContent(
        uiState = uiState,
        onBackNavIconClick = onBackNavIconClick,
        onLocationClick = onLocationClick,
        onEpisodeClick = onEpisodeClick
    )
}

@Composable
private fun DetailsContent(
    uiState: DetailsScreenUiState,
    onBackNavIconClick: Callback,
    onLocationClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithBackNavIcon(
                title = stringResource(StringRes.character_details),
                onBackNavigationIconClick = onBackNavIconClick
            )
        },
        content = { paddingValues ->
            when (uiState) {
                is DetailsScreenUiState.Loading -> {
                    LoadingScreen(paddingValues = paddingValues)
                }

                is DetailsScreenUiState.Content -> {
                    Content(
                        paddingValues = paddingValues,
                        data = uiState,
                        onLocationClick = onLocationClick,
                        onEpisodeClick = onEpisodeClick,
                    )
                }

                is DetailsScreenUiState.Error -> {
                    ErrorScreen(
                        paddingValues = paddingValues,
                        title = uiState.title,
                        description = uiState.description
                    )
                }
            }
        }
    )
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    data: DetailsScreenUiState.Content,
    onLocationClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(all = dimensionResource(DimenRes.dimen_10))
    ) {
        item {
            PosterCardWithLabel(
                modifier = Modifier.height(dimensionResource(DimenRes.dimen_350)),
                imageToLoad = data.character.image,
                label = {
                    CharacterLabel(
                        characterDisplayName = data.character.characterDisplayLabel,
                        characterStatus = data.character.status
                    )
                }
            )

            Spacer(modifier = Modifier.height(dimensionResource(DimenRes.dimen_10)))

            CharacterDetailsRow(
                species = data.character.species,
                gender = data.character.gender,
                origin = data.originInfo,
                currentLocation = data.locationInfo,
                onLocationClick = onLocationClick
            )
        }

        displayEpisodeList(data.episodes, onEpisodeClick)
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsContentPreview() {
    val uiState = DetailsScreenUiState.Content(
        character = DUMMY_CHARACTER,
        originInfo = DUMMY_LOCATION,
        locationInfo = DUMMY_LOCATION,
        episodes = listOf(DUMMY_EPISODE)
    )
    DetailsContent(
        uiState = uiState,
        onBackNavIconClick = {},
        onLocationClick = {},
        onEpisodeClick = {}
    )
}
