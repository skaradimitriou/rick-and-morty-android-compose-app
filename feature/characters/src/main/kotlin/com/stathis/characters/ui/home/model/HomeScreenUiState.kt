package com.stathis.characters.ui.home.model

import com.stathis.model.characters.CharacterResponse

sealed class HomeScreenUiState {

    data object Loading : HomeScreenUiState()

    data class Content(val data: List<CharacterResponse>) : HomeScreenUiState()

    data class Error(val title: String, val description: String) : HomeScreenUiState()
}
