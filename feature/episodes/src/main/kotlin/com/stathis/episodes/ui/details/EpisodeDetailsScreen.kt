package com.stathis.episodes.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.stathis.common.util.Callback
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.common.util.toNotNull
import com.stathis.designsystem.components.cards.BasicCardWithText
import com.stathis.ui.CharacterDisplayCard
import com.stathis.ui.topbars.TopBarWithBackNavIcon

@Composable
internal fun EpisodeDetailsScreen(
    episodeId: Int,
    viewModel: EpisodeDetailsViewModel = hiltViewModel(),
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    val state = viewModel.episodes.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.fetchEpisodeDetails(episodeId)
    }

    EpisodeDetailsContent(
        state = state.value,
        onBackNavIconClick = onBackNavIconClick,
        onCharacterClick = onCharacterClick
    )
}

@Composable
internal fun EpisodeDetailsContent(
    state: EpisodeDetailsViewModel.UiState,
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithBackNavIcon(
                title = stringResource(StringRes.episode_details),
                onBackNavigationIconClick = onBackNavIconClick
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    BasicCardWithText(
                        modifier = Modifier.padding(
                            top = dimensionResource(DimenRes.dimen_8),
                            start = dimensionResource(DimenRes.dimen_16),
                            end = dimensionResource(DimenRes.dimen_16),
                        ),
                        title = state.episodeDetails?.episode?.name + " | " + state.episodeDetails?.episode?.episode,
                        description = state.episodeDetails?.episode?.airDate.toNotNull()
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = dimensionResource(DimenRes.dimen_16),
                            vertical = dimensionResource(DimenRes.dimen_16)
                        ),
                        text = "Characters",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    )
                }

                state.episodeDetails?.characters?.let { characters ->
                    items(characters) { character ->
                        CharacterDisplayCard(
                            modifier = Modifier.padding(
                                top = dimensionResource(DimenRes.dimen_8),
                                start = dimensionResource(DimenRes.dimen_16),
                                end = dimensionResource(DimenRes.dimen_16),
                            ),
                            character = character,
                            onCharacterClick = onCharacterClick
                        )
                    }
                }
            }
        }
    )
}
