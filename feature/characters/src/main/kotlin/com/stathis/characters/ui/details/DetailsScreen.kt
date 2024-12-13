package com.stathis.characters.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.components.displayEpisodeList
import com.stathis.characters.ui.details.components.CharacterDetailsCard
import com.stathis.characters.ui.details.components.Detail
import com.stathis.characters.ui.details.model.DetailsScreenUiState
import com.stathis.common.util.Callback
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.common.util.toNotNull
import com.stathis.testing.CharactersFakes
import com.stathis.testing.EpisodeFakes
import com.stathis.ui.error.ErrorScreen
import com.stathis.ui.loading.LoadingScreen
import com.stathis.ui.topbars.TopBarWithBackNavIcon
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun DetailsScreen(
    characterId: Int,
    viewModel: DetailsScreenViewModel = koinViewModel(),
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
private fun DetailsContent(
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
private fun Content(
    paddingValues: PaddingValues,
    data: DetailsScreenUiState.Content,
    onEpisodeClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(all = dimensionResource(DimenRes.dimen_10))
    ) {
        data.character?.let { character ->
            item {
                CharacterDetailsCard(
                    modifier = Modifier
                        .height(350.dp)
                        .padding(bottom = 12.dp),
                    character = character,
                )

                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(DimenRes.dimen_10))
                ) {
                    Detail(
                        modifier = Modifier.weight(1f),
                        title = stringResource(StringRes.species),
                        description = character.species.toNotNull()
                    )

                    Detail(
                        modifier = Modifier.weight(1f),
                        title = stringResource(StringRes.gender),
                        description = character.gender.toNotNull()
                    )

                    Detail(
                        modifier = Modifier.weight(1f),
                        title = stringResource(StringRes.origin),
                        description = character.origin.toNotNull()
                    )
                }
            }
        }

        data.episodes?.let { episodes ->
            displayEpisodeList(episodes, onEpisodeClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsContentPreview() {
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
