package com.stathis.characters.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.ui.details.components.BasicCharacterInfo
import com.stathis.characters.ui.details.components.CharacterDetails
import com.stathis.characters.ui.details.model.DetailsScreenUiState
import com.stathis.common.util.Callback
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.cards.BasicCardWithText
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        item {
                            BasicCharacterInfo(character = uiState.character)
                            CharacterDetails(character = uiState.character)

                            Text(
                                modifier = Modifier.padding(
                                    top = dimensionResource(DimenRes.dimen_16),
                                    start = dimensionResource(DimenRes.dimen_16)
                                ),
                                text = "Episodes",
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                                )
                            )
                        }

                        uiState.episodes?.let {
                            items(items = it) {
                                BasicCardWithText(
                                    modifier = Modifier.padding(
                                        top = dimensionResource(DimenRes.dimen_8),
                                        start = dimensionResource(DimenRes.dimen_16),
                                        end = dimensionResource(DimenRes.dimen_16),
                                    ),
                                    title = it.name + " | " + it.episode,
                                    description = it.airDate,
                                    onItemClick = { onEpisodeClick(it.id) }
                                )
                            }
                        }
                    }
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
