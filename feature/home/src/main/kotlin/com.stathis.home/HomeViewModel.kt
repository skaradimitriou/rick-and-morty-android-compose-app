package com.stathis.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.domain.usecases.FetchAllCharactersUseCase
import com.stathis.model.characters.CharacterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val useCase: FetchAllCharactersUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow(UiState())

    val characters = _characters.asStateFlow()

    fun fetchAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke().collect { data ->
                _characters.update {
                    it.copy(
                        results = data
                    )
                }
            }
        }
    }

    data class UiState(
        val results: List<CharacterResponse> = listOf()
    )
}