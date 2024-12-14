package com.example.meet_9.ui.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet_9.repository.RepositoryMhs
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMhsViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){

    var updateUiState by mutableStateOf(MhsUIState))
            private set
    private val _nim: String = checkNotNull(savedStateHandle[DestinasiEdit.NIM])

    init {
        viewModelScope.launch {
            updateUiState = repositoryMhs.getMahasiswa(_nim)
                .filterNotNull()
                .first()
                .toUiStateMhs()
        }
    }

    fun updateState(mahasiswaEvent: MahasiswaEvent){
        updateUiState = updateUiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }
}