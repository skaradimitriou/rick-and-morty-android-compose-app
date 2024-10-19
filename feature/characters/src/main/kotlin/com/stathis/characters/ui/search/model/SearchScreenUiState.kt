package com.stathis.characters.ui.search.model

import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode

sealed class SearchScreenUiState {

    data object None : SearchScreenUiState()

    data object Loading : SearchScreenUiState()

    data class Content(
        val characters: List<CharacterResponse>?,
        val episodes: List<Episode>?
    ) : SearchScreenUiState()

    data class Error(val title: String, val description: String) : SearchScreenUiState()
}
