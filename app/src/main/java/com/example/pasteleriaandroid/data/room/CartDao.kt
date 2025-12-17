package com.example.pasteleriaandroid.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {

    @Insert
    suspend fun addToCart(item: CartItemEntity): Long

    @Query("SELECT * FROM cart_items WHERE clienteId = :clienteId")
    suspend fun getCartByCliente(clienteId: Int): List<CartItemEntity>

    @Query("UPDATE cart_items SET quantity = :quantity WHERE id = :cartItemId")
    suspend fun updateQuantity(cartItemId: Int, quantity: Int)

    @Query("DELETE FROM cart_items WHERE id = :cartItemId")
    suspend fun remove(cartItemId: Int)

    @Query("DELETE FROM cart_items WHERE clienteId = :clienteId")
    suspend fun clear(clienteId: Int)
}
