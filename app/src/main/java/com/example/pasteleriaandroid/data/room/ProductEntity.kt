package com.example.pasteleriaandroid.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// 👇 aquí forzamos a que la tabla se llame "productos"
@Entity(tableName = "productos")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: String = ""
)
