package com.stathis.characters.ui.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.stathis.common.util.Callback
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.cards.BasicCardWithText
import com.stathis.designsystem.components.topbar.CustomTopAppBar
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.characters.CharacterStatus
import com.stathis.model.episodes.Episode

@Composable
internal fun DetailsScreen(
    characterId: Int,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    onBackNavIconClick: Callback
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.fetchCharacterDetails(characterId)
    }

    DetailsContent(
        character = uiState.character,
        episodes = uiState.episodes,
        onBackNavIconClick = onBackNavIconClick
    )
}

@Composable
internal fun DetailsContent(
    character: CharacterResponse?,
    episodes: List<Episode>,
    onBackNavIconClick: Callback
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                startIcon = Icons.Default.ArrowBack,
                startIconContentDesc = "Back Navigation Arrow",
                startIconCallback = onBackNavIconClick,
                title = stringResource(StringRes.details)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    BasicCharacterInfo(character = character)
                    CharacterDetails(character = character)

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

                items(items = episodes) {
                    BasicCardWithText(
                        modifier = Modifier.padding(
                            top = dimensionResource(DimenRes.dimen_8),
                            start = dimensionResource(DimenRes.dimen_16),
                            end = dimensionResource(DimenRes.dimen_16),
                        ),
                        title = it.name + " | " + it.episode,
                        description = it.airDate
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
internal fun DetailsContentPreview() {
    val model = CharacterResponse(
        123, "Character Name", CharacterStatus.ALIVE, "Human", "Type",
        "Male", "Somewhere", "Earth", "", listOf("8"), "", ""
    )

    DetailsContent(
        character = model,
        episodes = listOf(),
        onBackNavIconClick = {}
    )
}
