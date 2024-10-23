package com.stathis.characters.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.components.displayCharacterList
import com.stathis.characters.components.displayEpisodeList
import com.stathis.characters.components.displayLocationsList
import com.stathis.characters.ui.search.model.SearchScreenUiState
import com.stathis.common.util.Callback
import com.stathis.common.util.DimenRes
import com.stathis.designsystem.components.search.CustomSearchBar
import com.stathis.ui.error.ErrorScreen
import com.stathis.ui.loading.LoadingScreen
import com.stathis.ui.topbars.TopBarWithBackNavIcon

@Composable
internal fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onBackNavIconClick: Callback,
    onCharacterClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit,
) {
    val suggestions by viewModel.suggestions.collectAsStateWithLifecycle()
    val results by viewModel.results.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.getAllUserQueries()
    }

    SearchContent(
        suggestions = suggestions,
        results = results,
        onBackNavIconClick = onBackNavIconClick,
        onQuerySubmitted = viewModel::fetchQueryResults,
        onSuggestionClick = viewModel::fetchQueryResults,
        onCharacterClick = onCharacterClick,
        onEpisodeClick = onEpisodeClick
    )
}

@Composable
internal fun SearchContent(
    suggestions: List<String>,
    results: SearchScreenUiState,
    onBackNavIconClick: Callback,
    onQuerySubmitted: (String) -> Unit,
    onSuggestionClick: (String) -> Unit,
    onCharacterClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarWithBackNavIcon(
                title = "Search",
                onBackNavigationIconClick = onBackNavIconClick
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CustomSearchBar(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(bottom = dimensionResource(DimenRes.dimen_16)),
                    hint = "Search ..",
                    suggestions = suggestions,
                    onQuerySubmitted = onQuerySubmitted,
                    onSuggestionClick = onSuggestionClick
                )

                when (results) {
                    is SearchScreenUiState.None -> {
                        //FIXME: Add content for init screen state
                    }

                    is SearchScreenUiState.Loading -> {
                        LoadingScreen(paddingValues = paddingValues)
                    }

                    is SearchScreenUiState.Content -> {
                        SearchScreenContent(
                            results = results,
                            onCharacterClick = onCharacterClick,
                            onEpisodeClick = onEpisodeClick
                        )
                    }

                    is SearchScreenUiState.Error -> {
                        ErrorScreen(
                            paddingValues = paddingValues,
                            title = results.title,
                            description = results.description
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    results: SearchScreenUiState.Content,
    onCharacterClick: (Int) -> Unit,
    onEpisodeClick: (Int) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        results.characters?.let { characters ->
            displayCharacterList(characters, onCharacterClick)
        }

        results.episodes?.let { episodes ->
            displayEpisodeList(episodes, onEpisodeClick)
        }

        results.locations.let { locations ->
            displayLocationsList(locations = locations)
        }
    }
}
