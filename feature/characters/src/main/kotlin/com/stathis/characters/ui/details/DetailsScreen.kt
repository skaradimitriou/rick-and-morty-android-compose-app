package com.stathis.characters.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.ui.details.components.BasicCharacterInfo
import com.stathis.characters.ui.details.components.CharacterDetails
import com.stathis.characters.components.displayEpisodeList
import com.stathis.characters.ui.details.model.DetailsScreenUiState
import com.stathis.common.util.Callback
import com.stathis.common.util.StringRes
import com.stathis.testing.CharactersFakes
import com.stathis.testing.EpisodeFakes
import com.stathis.ui.error.ErrorScreen
import com.stathis.ui.loading.LoadingScreen
import com.stathis.ui.topbars.TopBarWithBackNavIcon

@Composable
internal fun DetailsScreen(
    characterId: Int,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBackNavIconClick: Callback,
    onEpisodeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.fetchCharacterDetails(characterId)
    }

    DetailsContent(
        uiState = uiState,
        onBackNavIconClick = onBackNavIconClick,
        onEpisodeClick = onEpisodeClick
    )
}

@Composable
internal fun DetailsContent(
    uiState: DetailsScreenUiState,
    onBackNavIconClick: Callback,
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
                        onEpisodeClick = onEpisodeClick
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
internal fun Content(
    paddingValues: PaddingValues,
    data: DetailsScreenUiState.Content,
    onEpisodeClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        data.character?.let {
            item {
                BasicCharacterInfo(character = it)
                CharacterDetails(character = it)
            }
        }

        data.episodes?.let { episodes ->
            displayEpisodeList(episodes, onEpisodeClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun DetailsContentPreview() {
    val uiState = DetailsScreenUiState.Content(
        character = CharactersFakes.provideDummyCharacter(),
        episodes = EpisodeFakes.provideDummyEpisodeList()
    )
    DetailsContent(
        uiState = uiState,
        onBackNavIconClick = {},
        onEpisodeClick = {}
    )
}
