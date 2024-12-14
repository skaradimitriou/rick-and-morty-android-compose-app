package com.stathis.characters.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.characters.ui.details.model.DetailsScreenUiState
import com.stathis.domain.usecases.characters.FetchCharacterDetailsUseCase
import com.stathis.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class DetailsScreenViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchCharacterDetailsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsScreenUiState> = MutableStateFlow(DetailsScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun fetchCharacterDetails(id: Int) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(id)
                .collect { result ->
                    _uiState.update { result.toUiState() }
                }
        }
    }

    private fun Result<FetchCharacterDetailsUseCase.CharacterDetails>.toUiState() = when (this) {
        is Result.Loading -> DetailsScreenUiState.Loading
        is Result.Success -> DetailsScreenUiState.Content(
            character = data.character,
            episodes = data.episodes
        )

        is Result.Error -> DetailsScreenUiState.Error(
            title = "Something went wrong",
            description = exception.message.toString()
        )
    }
}
