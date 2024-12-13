package com.stathis.characters.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.characters.ui.details.model.DetailsScreenUiState
import com.stathis.domain.usecases.characters.FetchCharacterDetailsUseCase
import com.stathis.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class DetailsScreenViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchCharacterDetailsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsScreenUiState> = MutableStateFlow(DetailsScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    suspend fun fetchCharacterDetails(id: Int) {
        useCase.invoke(id)
            .onEach { result -> _uiState.update { result.toUiState() } }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
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
