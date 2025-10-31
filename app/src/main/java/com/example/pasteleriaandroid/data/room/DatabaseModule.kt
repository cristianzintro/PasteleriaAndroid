package com.example.pasteleriaandroid.data.room

import android.content.Context
import androidx.room.Room

object DatabaseModule {
    private var INSTANCE: MilSaboresDb? = null

    fun getDatabase(context: Context): MilSaboresDb {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MilSaboresDb::class.java,
                "mil_sabores_db"
            ).fallbackToDestructiveMigration().build()
            INSTANCE = instance
            instance
        }
    }
}
