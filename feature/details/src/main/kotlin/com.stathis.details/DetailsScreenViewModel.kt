package com.stathis.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(""))

    val uiState = _uiState.asStateFlow()

    fun fetchCharacterInformation() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    data class UiState(
        val data: String
    )
}