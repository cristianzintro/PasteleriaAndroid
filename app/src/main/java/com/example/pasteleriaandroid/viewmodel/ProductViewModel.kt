package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.DatabaseModule
import com.example.pasteleriaandroid.data.room.ProductEntity
import com.example.pasteleriaandroid.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

class ProductViewModel(app: Application) : AndroidViewModel(app) {

    private val db = DatabaseModule.getDatabase(app)
    private val productDao = db.productDao()
    private val repository = ProductRepository(productDao)

    private val _productos = MutableStateFlow<List<ProductEntity>>(emptyList())
    val productos: StateFlow<List<ProductEntity>> = _productos

    init {
        viewModelScope.launch {
            // llena la tabla si está vacía
            repository.seedIfEmpty()

            // escucha los cambios de Room
            repository.getProductos().collectLatest { lista ->
                _productos.value = lista
            }
        }
    }
}
