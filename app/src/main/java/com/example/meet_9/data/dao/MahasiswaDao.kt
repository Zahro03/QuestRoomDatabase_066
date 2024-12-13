package com.example.meet_9.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.meet_9.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

@Dao
interface  MahasiswaDao {
    //getAllMahasiswa
    @Query("SELECT * FROM mahasiswa ORDER BY nama ASC")
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>

    //getMahasiswa
    @Query("SELECT * FROM mahasiswa WHERE nim = :nim")
    fun getMahasiswa(nim: String) : Flow<Mahasiswa>
}