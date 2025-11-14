package com.example.pasteleriaandroid.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clienteId: Int,   // de la tabla clientes
    val productId: Int,   // de la tabla products
    val quantity: Int
)
