package com.stathis.characters.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.ui.home.components.CharacterList
import com.stathis.characters.ui.home.model.HomeScreenUiState
import com.stathis.common.util.Callback
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.topbar.CustomTopAppBar
import com.stathis.designsystem.theme.RickAndMortyAppTheme
import com.stathis.testing.CharactersFakes
import com.stathis.ui.error.ErrorScreen
import com.stathis.ui.loading.LoadingScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onCharacterClick: (Int) -> Unit,
    onSearchIconClick: Callback
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        uiState = uiState,
        onCharacterClick = onCharacterClick,
        onSearchIconClick = onSearchIconClick
    )
}

@Composable
private fun HomeContent(
    uiState: HomeScreenUiState,
    onCharacterClick: (Int) -> Unit,
    onSearchIconClick: Callback
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = stringResource(StringRes.home),
                endIcon = Icons.Default.Search,
                endIconCallback = onSearchIconClick
            )
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
private fun HomeScreenLoadingPreview() {
    RickAndMortyAppTheme {
        HomeContent(
            uiState = HomeScreenUiState.Loading,
            onCharacterClick = {},
            onSearchIconClick = {}
        )
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    RickAndMortyAppTheme {
        val data = CharactersFakes.provideDummyCharacterList()
        HomeContent(
            uiState = HomeScreenUiState.Content(data = data),
            onCharacterClick = {},
            onSearchIconClick = {}
        )
    }
}

@Preview
@Composable
private fun HomeScreenErrorPreview() {
    RickAndMortyAppTheme {
        HomeContent(
            uiState = HomeScreenUiState.Error(
                title = "Something went wrong",
                description = "Server timeout."
            ),
            onCharacterClick = {},
            onSearchIconClick = {}
        )
    }
}
