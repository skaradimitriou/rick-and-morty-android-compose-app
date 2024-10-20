package com.stathis.characters.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.ui.home.components.CharacterList
import com.stathis.characters.ui.home.model.HomeScreenUiState
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.topbar.CustomTopAppBar
import com.stathis.designsystem.theme.RickAndMortyAppTheme
import com.stathis.testing.CharactersFakes
import com.stathis.ui.error.ErrorScreen
import com.stathis.ui.loading.LoadingScreen

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCharacterClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.fetchAllCharacters()
    }

    HomeContent(
        uiState = uiState,
        onCharacterClick = onCharacterClick
    )
}

@Composable
internal fun HomeContent(
    uiState: HomeScreenUiState,
    onCharacterClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(title = stringResource(StringRes.home))
        },
        content = { paddingValues ->
            when (uiState) {
                is HomeScreenUiState.Loading -> {
                    LoadingScreen(paddingValues = paddingValues)
                }

                is HomeScreenUiState.Content -> {
                    CharacterList(
                        paddingValues = paddingValues,
                        characters = uiState.data,
                        onCharacterClick = onCharacterClick
                    )
                }

                is HomeScreenUiState.Error -> {
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

@Preview
@Composable
internal fun HomeScreenLoadingPreview() {
    RickAndMortyAppTheme {
        HomeContent(
            uiState = HomeScreenUiState.Loading,
            onCharacterClick = {}
        )
    }
}

@Preview
@Composable
internal fun HomeScreenContentPreview() {
    RickAndMortyAppTheme {
        val data = CharactersFakes.provideDummyCharacterList()
        HomeContent(
            uiState = HomeScreenUiState.Content(data = data),
            onCharacterClick = {}
        )
    }
}

@Preview
@Composable
internal fun HomeScreenErrorPreview() {
    RickAndMortyAppTheme {
        HomeContent(
            uiState = HomeScreenUiState.Error(
                title = "Something went wrong",
                description = "Server timeout."
            ),
            onCharacterClick = {}
        )
    }
}
