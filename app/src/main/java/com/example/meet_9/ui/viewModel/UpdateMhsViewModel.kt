package com.example.meet_9.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet_9.data.entity.Mahasiswa
import com.example.meet_9.repository.RepositoryMhs
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

object DestinasiEdit {
    const val NIM = "nim"
}

class UpdateMhsViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){

    var updateUiState by mutableStateOf(MhsUIState())
            private set
    private val _nim: String = savedStateHandle[DestinasiEdit.NIM] ?: run {
        throw IllegalArgumentException("NIM is required but not found in savedStateHandle")
    }

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

    fun  validateFields() : Boolean{
        val event = updateUiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong"
        )
        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData(){
        val  currentEvent = updateUiState.mahasiswaEvent

        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMhs.updateMahasiswa(currentEvent.toMahasiswaEntity())
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur : ${updateUiState.snackBarMessage}")
                } catch (e:Exception){
                    updateUiState = updateUiState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUiState = updateUiState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage(){
        updateUiState = updateUiState.copy(snackBarMessage = null)
    }
}

fun Mahasiswa.toUiStateMhs() : MhsUIState = MhsUIState(
    mahasiswaEvent = this.toDetailUiEvent(),
)
