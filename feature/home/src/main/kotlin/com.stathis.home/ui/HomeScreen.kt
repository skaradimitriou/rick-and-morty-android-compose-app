package com.stathis.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.common.util.StringRes
import com.stathis.designsystem.components.topbar.CustomTopAppBar
import com.stathis.home.ui.components.CharacterList

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
internal fun HomeContent(
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