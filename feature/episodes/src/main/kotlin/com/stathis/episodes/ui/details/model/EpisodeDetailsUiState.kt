package com.stathis.episodes.ui.details.model

import com.stathis.domain.usecases.episodes.FetchEpisodeDetailsUseCase

internal sealed class EpisodeDetailsUiState {

    data object Loading : EpisodeDetailsUiState()

    data class Content(val data: FetchEpisodeDetailsUseCase.EpisodeDetails) : EpisodeDetailsUiState()

    data class Error(val errorTitle: String, val errorDescription: String) : EpisodeDetailsUiState()
}
