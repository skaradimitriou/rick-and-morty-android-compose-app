package com.stathis.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.common.util.DimenRes
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.cards.BasicCardWithImageAndText
import com.stathis.designsystem.components.topbar.CustomTopAppBar
import com.stathis.model.characters.CharacterResponse

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCharacterClick: (Int) -> Unit
) {
    val uiState by viewModel.characters.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.fetchAllCharacters()
    }

    HomeContent(
        uiState = uiState,
        onCharacterClick = onCharacterClick
    )
}


@Composable
fun HomeContent(
    uiState: HomeViewModel.UiState,
    onCharacterClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(title = stringResource(StringRes.home))
        },
        content = { paddingValues ->
            CharacterList(
                paddingValues = paddingValues,
                characters = uiState.results,
                onCharacterClick = onCharacterClick
            )
        }
    )
}

@Composable
fun CharacterList(
    paddingValues: PaddingValues,
    characters: List<CharacterResponse>,
    onCharacterClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .navigationBarsPadding()
            .padding(horizontal = dimensionResource(DimenRes.dimen_8))
            .padding(top = paddingValues.calculateTopPadding())
    ) {
        items(items = characters) { character ->
            BasicCardWithImageAndText(
                title = character.name,
                description = character.species,
                imageUrl = character.image,
                contentDescription = character.name,
                onClick = {
                    onCharacterClick(character.id)
                }
            )
        }
    }
}