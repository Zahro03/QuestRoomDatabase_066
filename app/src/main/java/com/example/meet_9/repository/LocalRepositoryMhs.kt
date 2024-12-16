package com.example.meet_9.repository

import com.example.meet_9.data.dao.MahasiswaDao
import com.example.meet_9.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMhs(
    private val mahasiswaDao: MahasiswaDao
): RepositoryMhs{
    override suspend fun insertMhs(mahasiswa: Mahasiswa){
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }

    override fun getAllMahasiswa(): Flow<List<Mahasiswa>> {
       return mahasiswaDao.getAllMahasiswa()
    }

    override fun getMahasiswa(nim: String): Flow<Mahasiswa> {
        return mahasiswaDao.getMahasiswa(nim)
    }

    override suspend fun deleteMahasiswa(mahasiswa: Mahasiswa) {
       mahasiswaDao.deleteMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaDao.updateMahasiswa(mahasiswa)
    }
}