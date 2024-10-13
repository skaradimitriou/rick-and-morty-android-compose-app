package com.stathis.episodes.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.stathis.common.util.Callback
import com.stathis.common.util.StringRes
import com.stathis.episodes.ui.details.components.displayCharacters
import com.stathis.episodes.ui.details.components.displayEpisodeInfo
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
                state.episodeDetails?.episode?.let { episode ->
                    displayEpisodeInfo(episode = episode)
                }

                state.episodeDetails?.characters?.let { characters ->
                    displayCharacters(
                        characters = characters,
                        onCharacterClick = onCharacterClick
                    )
                }
            }
        }
    )
}
