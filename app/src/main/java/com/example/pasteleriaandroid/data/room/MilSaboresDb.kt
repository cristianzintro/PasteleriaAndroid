package com.example.pasteleriaandroid.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ClienteEntity::class,
        ProductEntity::class,
        CartItemEntity::class   // 👈 la nueva
    ],
    version = 5,               // súbir version
    exportSchema = false
)
abstract class MilSaboresDb : RoomDatabase() {

    abstract fun clienteDao(): ClienteDao
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao     // 👈 la nueva
}
