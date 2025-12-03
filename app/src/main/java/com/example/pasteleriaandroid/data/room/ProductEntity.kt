package com.example.pasteleriaandroid.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")   // 👈 tabla correcta
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val imagen: String   // 👈 URL de la imagen
)
