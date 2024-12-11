package com.example.meet_9.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.meet_9.data.dao.MahasiswaDao
import com.example.meet_9.data.entity.Mahasiswa

@Database(entities = [Mahasiswa::class],version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase(){

    //mendefiniskan fungsi untuk mengakses data mahasiswa
    abstract fun mahasiswaDao() : MahasiswaDao

    companion object {
        @Volatile // memastikan bahwa nilai variabel instance selalu samaa disemua thread
        private  var Instance: KrsDatabase? = null
        fun  getDatabase(context: Context): KrsDatabase{
            return  (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    KrsDatabase::class.java, // class database
                    "krsDatabase" // nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}