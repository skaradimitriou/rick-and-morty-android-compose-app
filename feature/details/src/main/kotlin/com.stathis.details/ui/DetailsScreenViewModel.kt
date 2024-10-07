package com.stathis.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.common.di.IoDispatcher
import com.stathis.common.util.toNotNull
import com.stathis.domain.usecases.characters.FetchCharacterDetailsUseCase
import com.stathis.model.characters.CharacterResponse
import com.stathis.model.episodes.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailsScreenViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchCharacterDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())

    val uiState = _uiState.asStateFlow()

    fun fetchCharacterDetails(id: Int) {
        viewModelScope.launch(dispatcher) {
            useCase.invoke(id)
                .collect { data ->
                    _uiState.update {
                        it.copy(
                            character = data.character,
                            episodes = data.episodes.toNotNull()
                        )
                    }
                }
        }
    }

    data class UiState(
        var character: CharacterResponse? = null,
        var episodes: List<Episode> = listOf()
    )
}
