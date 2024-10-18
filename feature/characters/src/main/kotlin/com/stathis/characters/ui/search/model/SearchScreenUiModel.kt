package com.stathis.characters.ui.search.model

import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode

sealed class SearchScreenUiModel {

    data object None : SearchScreenUiModel()

    data object Loading : SearchScreenUiModel()

    data class Content(
        val characters: List<CharacterResponse>?,
        val episodes: List<Episode>?
    ) : SearchScreenUiModel()

    data class Error(val title: String, val description: String) : SearchScreenUiModel()
}
