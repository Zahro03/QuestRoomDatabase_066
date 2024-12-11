package com.example.meet_9.repository

import com.example.meet_9.data.dao.MahasiswaDao
import com.example.meet_9.data.entity.Mahasiswa

class LocalRepositoryMhs(
    private val mahasiswaDao: MahasiswaDao
): RepositoryMhs{
    override suspend fun  insertMhs(mahasiswa: Mahasiswa){
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }
}