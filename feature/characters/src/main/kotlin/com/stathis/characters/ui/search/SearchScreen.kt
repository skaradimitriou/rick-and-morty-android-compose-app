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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.stathis.characters.components.displayCharacterList
import com.stathis.characters.components.displayEpisodeList
import com.stathis.characters.ui.search.model.SearchScreenUiModel
import com.stathis.common.util.Callback
import com.stathis.designsystem.components.search.CustomSearchBar
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
    results: SearchScreenUiModel,
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
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    hint = "Search for a character..",
                    suggestions = suggestions,
                    onQuerySubmitted = onQuerySubmitted,
                    onSuggestionClick = onSuggestionClick
                )

                when (results) {
                    is SearchScreenUiModel.None -> {

                    }

                    is SearchScreenUiModel.Loading -> {

                    }

                    is SearchScreenUiModel.Content -> {
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            results.characters?.let { characters ->
                                displayCharacterList(characters, onCharacterClick)
                            }

                            results.episodes?.let { episodes ->
                                displayEpisodeList(episodes, onEpisodeClick)
                            }
                        }
                    }

                    is SearchScreenUiModel.Error -> {

                    }
                }
            }
        }
    )
}
