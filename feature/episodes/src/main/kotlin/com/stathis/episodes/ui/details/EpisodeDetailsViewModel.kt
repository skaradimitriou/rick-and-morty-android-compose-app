package com.stathis.episodes.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.domain.usecases.episodes.FetchEpisodeDetailsUseCase
import com.stathis.episodes.ui.details.model.EpisodeDetailsUiState
import com.stathis.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class EpisodeDetailsViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchEpisodeDetailsUseCase
) : ViewModel() {

    private val _episodes = MutableStateFlow<EpisodeDetailsUiState>(EpisodeDetailsUiState.Loading)
    val episodes = _episodes.asStateFlow()

    fun fetchEpisodeDetails(episodeId: Int) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(episodeId).collect { result ->
                _episodes.update { result.toUiState() }
            }
        }
    }

    private fun Result<FetchEpisodeDetailsUseCase.EpisodeDetails>.toUiState() = when (this) {
        is Result.Loading -> EpisodeDetailsUiState.Loading
        is Result.Success -> EpisodeDetailsUiState.Content(data)
        is Result.Error -> EpisodeDetailsUiState.Error(
            errorTitle = "Something went wrong",
            errorDescription = exception.message.toString()
        )
    }
}
