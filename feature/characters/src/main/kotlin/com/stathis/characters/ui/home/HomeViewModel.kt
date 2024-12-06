package com.stathis.characters.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.characters.ui.home.model.HomeScreenUiState
import com.stathis.domain.usecases.characters.FetchAllCharactersUseCase
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchAllCharactersUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchAllCharacters() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { result ->
                _uiState.update { result.toUiState() }
            }
        }
    }

    private fun Result<List<CharacterResponse>>.toUiState() = when (this) {
        is Result.Loading -> HomeScreenUiState.Loading
        is Result.Success -> HomeScreenUiState.Content(data)
        is Result.Error -> HomeScreenUiState.Error(
            title = "Something went wrong",
            description = message
        )
    }
}
