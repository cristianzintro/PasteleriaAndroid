package com.example.pasteleriaandroid.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// 👇 aquí forzamos a que la tabla se llame "productos"
@Entity(tableName = "productos")
data class ProductEntity(
    @PrimaryKey val id: Int,
    val codigo: String,
    val nombre: String,
    val categoria: String,
    val precio: Int,
    val descripcion: String,
    val imagen: String
)

