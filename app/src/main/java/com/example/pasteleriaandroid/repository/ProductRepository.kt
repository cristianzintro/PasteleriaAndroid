package com.example.pasteleriaandroid.repository

import com.example.pasteleriaandroid.data.room.ProductDao
import com.example.pasteleriaandroid.data.room.ProductEntity

class ProductRepository(private val dao: ProductDao) {

    fun getProductos() = dao.getAll()

    suspend fun seedIfEmpty() {
        val count = dao.count()
        if (count == 0) {
            dao.insertAll(
                listOf(
                    ProductEntity(
                        nombre = "Torta de chocolate",
                        descripcion = "Bizcocho húmedo con ganache de cacao.",
                        precio = 8500.0,
                        imagen = "torta_chocolate".trim()
                    ),
                    ProductEntity(
                        nombre = "Tarta de fresa",
                        descripcion = "Base de galleta con crema pastelera y fresas.",
                        precio = 7200.0,
                        imagen = "tarta_fresa".trim()
                    ),
                    ProductEntity(
                        nombre = "Mil hojas",
                        descripcion = "Capas de hojaldre y crema pastelera.",
                        precio = 6800.0,
                        imagen = "mil_hojas".trim()
                    )
                )
            )
        }
    }
}
