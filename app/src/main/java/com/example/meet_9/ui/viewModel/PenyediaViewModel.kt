package com.example.pam_roomlocaldb_andini.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.meet_9.KrsApp
import com.example.meet_9.ui.viewModel.DetailMhsViewModel
import com.example.meet_9.ui.viewModel.HomeMhsViewModel
import com.example.meet_9.ui.viewModel.MahasiswaViewModel
import com.example.meet_9.ui.viewModel.UpdateMhsViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            MahasiswaViewModel(
                krsApp().containerApp.repositoryMhs
            )
        }
        initializer {
            HomeMhsViewModel(
                krsApp().containerApp.repositoryMhs
            )
        }
        initializer {
            DetailMhsViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryMhs,
            )
        }
        initializer {
            UpdateMhsViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryMhs,
            )
        }
    }
}

fun CreationExtras.krsApp(): KrsApp = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)