package com.example.meet_9.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet_9.data.entity.Mahasiswa
import com.example.meet_9.repository.RepositoryMhs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class HomeMhsViewModel (
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = repositoryMhs.getAllMahasiswa()
        .filterNotNull()
        .map {
            HomeUiState (
                listMhs = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isLoading = true,
            )
        )
}

data class HomeUiState (
    val listMhs: List<Mahasiswa> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)