package com.stathis.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.domain.usecases.characters.FetchCharacterByIdUseCase
import com.stathis.domain.usecases.episodes.FetchEpisodesByIdUseCase
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val useCase: FetchCharacterByIdUseCase,
    private val episodesUseCase: FetchEpisodesByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())

    val uiState = _uiState.asStateFlow()

    fun fetchCharacterInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            //FIXME: Temp CharacterArg. Remove this late ron
            val tempCharacterArg = 183 //johny depp
            useCase.invoke(tempCharacterArg).collect { character ->
                _uiState.update {
                    it.copy(
                        character = character
                    )
                }
            }
        }
    }

    fun fetchEpisodesForCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            //FIXME: Temp CharacterArg. Remove this late ron
            val tempIds = listOf(28, 32) //johny depp
            episodesUseCase.invoke(tempIds).collect { episodes ->
                _uiState.update {
                    it.copy(
                        episodes = episodes
                    )
                }
            }
        }
    }

    data class UiState(
        var character: CharacterResponse? = null,
        var episodes: List<Episode>? = null
    )
}
