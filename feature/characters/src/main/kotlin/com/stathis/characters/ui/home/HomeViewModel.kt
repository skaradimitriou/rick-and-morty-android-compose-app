package com.stathis.characters.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.common.di.IoDispatcher
import com.stathis.domain.usecases.characters.FetchAllCharactersUseCase
import com.stathis.model.Result
import com.stathis.model.characters.CharacterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val useCase: FetchAllCharactersUseCase
) : ViewModel() {

    private val _characters = MutableStateFlow(UiState())

    val characters = _characters.asStateFlow()

    fun fetchAllCharacters() {
        viewModelScope.launch(dispatcher) {
            useCase.invoke().collect { result ->
                _characters.update {
                    when (result) {
                        is Result.Loading -> it.copy(isLoading = true)
                        is Result.Success -> it.copy(isLoading = false, results = result.data)
                        is Result.Error -> it.copy(isLoading = false, errorMessage = result.message)
                    }
                }
            }
        }
    }

    data class UiState(
        var isLoading: Boolean = true,
        var results: List<CharacterResponse> = listOf(),
        val errorMessage: String = ""
    )
}
