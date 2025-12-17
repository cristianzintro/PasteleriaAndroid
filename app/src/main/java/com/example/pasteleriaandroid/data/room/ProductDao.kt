package com.example.pasteleriaandroid.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM productos")
    fun getAll(): Flow<List<ProductEntity>>

    @Query("SELECT COUNT(*) FROM productos")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM productos WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): ProductEntity?
}
