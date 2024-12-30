package com.stathis.characters.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.components.displayEpisodeList
import com.stathis.characters.ui.details.components.CharacterDetailsRow
import com.stathis.characters.ui.details.components.CharacterLabel
import com.stathis.characters.ui.details.model.DetailsScreenViewState
import com.stathis.designsystem.components.cards.PosterCardWithLabel
import com.stathis.testing.CharactersFakes
import com.stathis.testing.EpisodeFakes
import com.stathis.ui.error.ErrorScreen
import com.stathis.ui.loading.LoadingScreen
import com.stathis.ui.topbars.TopBarWithBackNavIcon
import com.stathis.util.util.Callback
import com.stathis.util.util.DimenRes
import com.stathis.util.util.StringRes
import com.stathis.util.util.toNotNull
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DetailsScreen(
    characterId: Int,
    viewModel: DetailsScreenViewModel = koinViewModel(parameters = { parametersOf(characterId) }),
    onBackNavIconClick: Callback,
    onEpisodeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DetailsContent(
        uiState = uiState,
        onBackNavIconClick = onBackNavIconClick,
        onEpisodeClick = onEpisodeClick
    )
}

@Composable
private fun DetailsContent(
    uiState: DetailsScreenViewState,
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
                is DetailsScreenViewState.Loading -> {
                    LoadingScreen(paddingValues = paddingValues)
                }

                is DetailsScreenViewState.Content -> {
                    Content(
                        paddingValues = paddingValues,
                        data = uiState,
                        onEpisodeClick = onEpisodeClick
                    )
                }

                is DetailsScreenViewState.Error -> {
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
    data: DetailsScreenViewState.Content,
    onEpisodeClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(all = dimensionResource(DimenRes.dimen_10))
    ) {
        item {
            Card {
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

                Column(
                    modifier = Modifier.padding(all = dimensionResource(DimenRes.dimen_10))
                ) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Information",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.W700
                        )
                    )
                    Spacer(Modifier.height(8.dp))
                    Row {

                    }
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(DimenRes.dimen_8)))

            CharacterDetailsRow(
                species = data.character.species.toNotNull(),
                gender = data.character.gender.toNotNull(),
                origin = data.character.origin.name.toNotNull()
            )
        }

        data.episodes?.let { episodes ->
            displayEpisodeList(episodes, onEpisodeClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsContentPreview() {
    val uiState = DetailsScreenViewState.Content(
        character = CharactersFakes.provideDummyCharacter(),
        episodes = EpisodeFakes.provideDummyEpisodeList()
    )
    DetailsContent(
        uiState = uiState,
        onBackNavIconClick = {},
        onEpisodeClick = {}
    )
}
