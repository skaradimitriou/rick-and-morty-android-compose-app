package com.stathis.episodes.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stathis.common.util.Callback
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.topbar.CustomTopAppBar

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
            CustomTopAppBar(
                startIcon = Icons.Default.ArrowBack,
                startIconContentDesc = "Back Navigation Arrow",
                startIconCallback = onBackNavIconClick,
                title = stringResource(StringRes.episode_details)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp)
                            .clickable {
                                //FIXME: Add logic
                            },
                        text = "Episode Details ${state.episodeDetails?.episode} \n\n " +
                                "Characters => ${state.episodeDetails?.characters}"
                    )
                }
            }
        }
    )
}
