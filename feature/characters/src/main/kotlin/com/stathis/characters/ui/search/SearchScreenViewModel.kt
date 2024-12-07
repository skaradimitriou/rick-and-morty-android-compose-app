package com.stathis.characters.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.characters.ui.search.model.SearchScreenUiState
import com.stathis.domain.usecases.search.FetchAllUserQueriesUseCase
import com.stathis.domain.usecases.search.FetchQueryResultsUseCase
import com.stathis.domain.usecases.search.FetchQueryResultsUseCase.QueryResult
import com.stathis.domain.usecases.search.SaveQueryToLocalDbUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SearchScreenViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val fetchAllUserQueriesUseCase: FetchAllUserQueriesUseCase,
    private val saveUserQueryUseCase: SaveQueryToLocalDbUseCase,
    private val fetchQueryResultsUseCase: FetchQueryResultsUseCase
) : ViewModel() {

    private val _suggestions: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    val suggestions = _suggestions.asStateFlow()

    private val _results: MutableStateFlow<SearchScreenUiState> = MutableStateFlow(SearchScreenUiState.None)
    val results = _results.asStateFlow()

    fun getAllUserQueries() {
        viewModelScope.launch(dispatcher) {
            fetchAllUserQueriesUseCase.invoke().collect { queries ->
                _suggestions.update { queries.map { it.name } }
            }
        }
    }

    fun fetchQueryResults(query: String) {
        viewModelScope.launch(dispatcher) {
            if (query.isNotEmpty()) {
                _results.update { SearchScreenUiState.Loading }
                async { saveUserQueryUseCase.invoke(query) }
                fetchQueryResultsUseCase.invoke(query).collect { result -> result.toUiState() }
            } else {
                _results.update { SearchScreenUiState.None }
            }
        }
    }

    private fun QueryResult.toUiState() = when (this) {
        is QueryResult.None -> _results.update { SearchScreenUiState.Loading }
        is QueryResult.Success -> _results.update {
            SearchScreenUiState.Content(
                characters = characters,
                episodes = episodes,
                locations = locations
            )
        }

        is QueryResult.Error -> _results.update {
            //FIXME: Fix UI Error
            SearchScreenUiState.Error(
                title = "Something went wrong",
                description = "Insert error description here"
            )
        }
    }
}
