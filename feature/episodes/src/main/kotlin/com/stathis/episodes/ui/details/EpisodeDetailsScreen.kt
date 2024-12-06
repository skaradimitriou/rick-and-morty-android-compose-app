package com.stathis.episodes.ui.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.stathis.common.util.Callback
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.designsystem.theme.RickAndMortyAppTheme
import com.stathis.domain.usecases.episodes.FetchEpisodeDetailsUseCase
import com.stathis.episodes.ui.details.components.displayCharacters
import com.stathis.episodes.ui.details.components.displayEpisodeInfo
import com.stathis.episodes.ui.details.model.EpisodeDetailsUiState
import com.stathis.testing.CharactersFakes
import com.stathis.testing.EpisodeFakes
import com.stathis.ui.error.ErrorScreen
import com.stathis.ui.loading.LoadingScreen
import com.stathis.ui.topbars.TopBarWithBackNavIcon
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun EpisodeDetailsScreen(
    episodeId: Int,
    viewModel: EpisodeDetailsViewModel = koinViewModel(),
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit
) {
    val state = viewModel.episodes.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.fetchEpisodeDetails(episodeId)
    }

    EpisodeDetailsContent(
        uiState = state.value,
        onBackNavIconClick = onBackNavIconClick,
        onCharacterClick = onCharacterClick
    )
}

@Composable
internal fun EpisodeDetailsContent(
    uiState: EpisodeDetailsUiState,
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
            when (uiState) {
                is EpisodeDetailsUiState.Loading -> {
                    LoadingScreen(paddingValues = paddingValues)
                }

                is EpisodeDetailsUiState.Content -> {
                    Content(
                        paddingValues = paddingValues,
                        data = uiState.data,
                        onCharacterClick = onCharacterClick
                    )
                }

                is EpisodeDetailsUiState.Error -> {
                    ErrorScreen(
                        paddingValues = paddingValues,
                        title = uiState.errorTitle,
                        description = uiState.errorDescription
                    )
                }
            }
        }
    )
}

@Composable
internal fun Content(
    paddingValues: PaddingValues,
    data: FetchEpisodeDetailsUseCase.EpisodeDetails,
    onCharacterClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        data.episode?.let { episode ->
            displayEpisodeInfo(episode = episode)
        }

        data.characters?.let { characters ->
            displayCharacters(
                characters = characters,
                onCharacterClick = onCharacterClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun EpisodeDetailsLoadingPreview() {
    RickAndMortyAppTheme {
        LoadingScreen(
            paddingValues = PaddingValues(all = dimensionResource(DimenRes.dimen_8))
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun EpisodeDetailsContentPreview() {
    RickAndMortyAppTheme {
        Content(
            paddingValues = PaddingValues(all = dimensionResource(DimenRes.dimen_8)),
            data = FetchEpisodeDetailsUseCase.EpisodeDetails(
                episode = EpisodeFakes.provideDummyEpisode(),
                characters = CharactersFakes.provideDummyCharacterList()
            ),
            onCharacterClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun EpisodeDetailsErrorPreview() {
    RickAndMortyAppTheme {
        ErrorScreen(
            paddingValues = PaddingValues(all = dimensionResource(DimenRes.dimen_8)),
            title = "Something went wrong",
            description = "Server timeout"
        )
    }
}
