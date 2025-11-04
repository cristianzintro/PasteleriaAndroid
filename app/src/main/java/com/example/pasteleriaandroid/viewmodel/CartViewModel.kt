package com.example.pasteleriaandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pasteleriaandroid.data.room.ProductEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<ProductEntity>>(emptyList())
    val items: StateFlow<List<ProductEntity>> = _items

    fun addProduct(product: ProductEntity) {
        _items.update { current ->
            val existing = current.find { it.id == product.id }
            if (existing != null) current else current + product
        }
    }

    fun removeProduct(product: ProductEntity) {
        _items.update { current -> current.filterNot { it.id == product.id } }
    }

    fun clearCart() {
        _items.value = emptyList()
    }

    fun total(): Double = _items.value.sumOf { it.precio }
}
