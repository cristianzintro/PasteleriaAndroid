package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.DatabaseModule
import com.example.pasteleriaandroid.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(app: Application) : AndroidViewModel(app) {

    private val db = DatabaseModule.getDatabase(app)
    private val repo = ProductRepository(db.productDao())

    val productos = repo.getProductos()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        // 👇 al crear el VM, aseguramos que haya productos
        viewModelScope.launch {
            repo.seedIfEmpty()
        }
    }
}
