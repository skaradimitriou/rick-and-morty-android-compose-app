package com.stathis.characters.ui.details.model

import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode

sealed class DetailsScreenUiState {

    data object Loading : DetailsScreenUiState()

    data class Content(
        val character: CharacterResponse?,
        val episodes: List<Episode>?
    ) : DetailsScreenUiState()

    data class Error(val title: String, val description: String) : DetailsScreenUiState()
}
