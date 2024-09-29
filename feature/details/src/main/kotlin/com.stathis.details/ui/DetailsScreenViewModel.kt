package com.stathis.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.domain.usecases.characters.FetchCharacterByIdUseCase
import com.stathis.model.characters.CharacterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val useCase: FetchCharacterByIdUseCase
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

    data class UiState(
        var character: CharacterResponse? = null
    )
}