package com.example.pasteleriaandroid.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val precio: Int,
    val descripcion: String,
    val imagen: String // url o nombre de recurso
)
