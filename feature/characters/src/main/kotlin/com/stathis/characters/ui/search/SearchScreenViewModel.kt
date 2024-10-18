package com.stathis.characters.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.characters.ui.search.model.SearchScreenUiModel
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.usecases.search.FetchQueryResultsUseCase
import com.stathis.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val fetchQueryResultsUseCase: FetchQueryResultsUseCase
) : ViewModel() {

    private val _suggestions = MutableStateFlow(listOf(""))
    val suggestions = _suggestions.asStateFlow()

    private val _results: MutableStateFlow<SearchScreenUiModel> = MutableStateFlow(SearchScreenUiModel.None)
    val results = _results.asStateFlow()

    fun getAllUserQueries() {
        _suggestions.update {
            listOf("Query 1", "Query 2", "Query 3", "Query 4", "Query 5")
        }
    }

    fun fetchQueryResults(query: String) {
        viewModelScope.launch(dispatcher) {
            if (query.isNotEmpty()) {
                fetchQueryResultsUseCase.invoke(query).collect { result ->
                    when (result) {
                        is Result.Loading -> _results.update { SearchScreenUiModel.Loading }

                        is Result.Success -> _results.update {
                            SearchScreenUiModel.Content(
                                characters = result.data.characters,
                                episodes = result.data.episodes,
                            )
                        }

                        is Result.Error -> _results.update {
                            SearchScreenUiModel.Error(
                                title = "Something went wrong",
                                description = result.message
                            )
                        }
                    }
                }
            } else {
                _results.update { SearchScreenUiModel.None }
            }
        }
    }

    fun saveUserQueryToLocalDb() {
        //
    }
}
