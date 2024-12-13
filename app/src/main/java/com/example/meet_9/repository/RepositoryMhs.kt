package com.example.meet_9.repository

import com.example.meet_9.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    //getAllMhs
    fun getAllMhs(): Flow<List<Mahasiswa>>

    //getMhs
    fun getMhs(nim: String): Flow<Mahasiswa>

    //deleteMhs
    suspend fun deleteMhs(mahasiswa:Mahasiswa)
}