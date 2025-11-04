package com.example.pasteleriaandroid.repository

import com.example.pasteleriaandroid.data.room.ProductDao
import com.example.pasteleriaandroid.data.room.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val dao: ProductDao) {
    fun getAll(): Flow<List<ProductEntity>> = dao.getAll()
    suspend fun upsertAll(items: List<ProductEntity>) = dao.upsertAll(items)
}
