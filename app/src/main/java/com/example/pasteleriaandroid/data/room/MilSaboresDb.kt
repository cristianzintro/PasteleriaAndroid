package com.example.pasteleriaandroid.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MilSaboresDb : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
