package com.example.pasteleriaandroid.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ProductEntity::class,
        ClienteEntity::class
    ],
    version = 2,               // <- subimos versión
    exportSchema = false
)
abstract class MilSaboresDb : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun clienteDao(): ClienteDao
}
