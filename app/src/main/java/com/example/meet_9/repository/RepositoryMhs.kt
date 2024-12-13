package com.example.meet_9.repository

import com.example.meet_9.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    fun getAllMhs(): Flow<List<Mahasiswa>>
}