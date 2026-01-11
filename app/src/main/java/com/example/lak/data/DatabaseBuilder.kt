package com.example.lak.data

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    @Volatile
    private var INSTANCE: AppDatabase? = null
    fun getInstance(context : Context): AppDatabase{
        return INSTANCE?: synchronized (this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "lak_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}