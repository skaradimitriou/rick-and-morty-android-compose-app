package com.stathis.episodes.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.usecases.episodes.FetchEpisodeDetailsUseCase
import com.stathis.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EpisodeDetailsViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchEpisodeDetailsUseCase
) : ViewModel() {

    private val _episodes: MutableStateFlow<UiState> = MutableStateFlow<UiState>(UiState())

    val episodes = _episodes.asStateFlow()

    fun fetchEpisodeDetails(episodeId: Int) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(episodeId).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _episodes.update { it.copy(isLoading = true) }
                    }

                    is Result.Success -> {
                        _episodes.update {
                            it.copy(isLoading = false, episodeDetails = result.data)
                        }
                    }

                    is Result.Error -> {
                        _episodes.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                }
            }
        }
    }

    data class UiState(
        var isLoading: Boolean = true,
        var episodeDetails: FetchEpisodeDetailsUseCase.EpisodeDetails? = null,
        var error: String = ""
    )
}
