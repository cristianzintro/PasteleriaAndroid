package com.example.pasteleriaandroid.repository

import com.example.pasteleriaandroid.data.room.ProductDao
import com.example.pasteleriaandroid.data.room.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val dao: ProductDao) {

    fun getProductos() = dao.getAll()

    suspend fun insertarProducto(product: ProductEntity) {
        dao.insert(product)
    }

    // 👇 esto es lo nuevo
    suspend fun seedIfEmpty() = withContext(Dispatchers.IO) {
        val count = dao.count()
        if (count == 0) {
            dao.insertAll(
                listOf(
                    ProductEntity(
                        nombre = "Torta de chocolate",
                        descripcion = "Bizcocho húmedo con ganache.",
                        precio = 8500.0,
                        imagen = "torta_chocolate"
                    ),
                    ProductEntity(
                        nombre = "Tarta de fresa",
                        descripcion = "Base de galleta con crema pastelera y fresas.",
                        precio = 7200.0,
                        imagen = "tarta_fresa"
                    ),
                    ProductEntity(
                        nombre = "Mil hojas",
                        descripcion = "Capas de hojaldre con crema pastelera.",
                        precio = 6800.0,
                        imagen = "mil_hojas"
                    )
                )
            )
        }
    }
}
