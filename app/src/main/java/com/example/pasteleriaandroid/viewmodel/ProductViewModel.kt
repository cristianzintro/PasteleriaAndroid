package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.DatabaseModule
import com.example.pasteleriaandroid.data.room.ProductEntity
import com.example.pasteleriaandroid.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ProductRepository(DatabaseModule.getDatabase(app).productDao())

    val productos = repo.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun seedData() = viewModelScope.launch {
        if (productos.value.isEmpty()) {
            repo.upsertAll(
                listOf(
                    ProductEntity(nombre = "Torta Mil Hojas", precio = 15000, descripcion = "Clásica con manjar y hojaldre", imagen = "torta1.jpg"),
                    ProductEntity(nombre = "Cheesecake Frambuesa", precio = 18000, descripcion = "Suave y con frutos rojos", imagen = "cheesecake.jpg"),
                    ProductEntity(nombre = "Brownie Chocolate", precio = 12000, descripcion = "Denso y húmedo, con nueces", imagen = "brownie.jpg"),
                )
            )
        }
    }
}
