package com.example.pasteleriaandroid.data.room

import android.content.Context
import androidx.room.Room

object DatabaseModule {
    @Volatile private var INSTANCE: MilSaboresDb? = null

    fun getDatabase(context: Context): MilSaboresDb =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                MilSaboresDb::class.java,
                "mil_sabores_db"
            ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
}
