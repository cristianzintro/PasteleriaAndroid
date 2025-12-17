package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.CartItemEntity
import com.example.pasteleriaandroid.data.room.DatabaseModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class CartUiItem(
    val id: Int,        // id del registro en cart_items
    val nombre: String,
    val precio: Int,
    val quantity: Int
)

class CartViewModel(app: Application) : AndroidViewModel(app) {

    private val db = DatabaseModule.getDatabase(app)
    private val cartDao = db.cartDao()
    private val productDao = db.productDao()

    private val _items = MutableStateFlow<List<CartUiItem>>(emptyList())
    val items: StateFlow<List<CartUiItem>> = _items

    fun loadCart(clienteId: Int) {
        viewModelScope.launch {
            val cartRows = cartDao.getCartByCliente(clienteId)
            val productos = productDao.getAll().first()

            _items.value = cartRows.mapNotNull { row ->
                val p = productos.find { it.id == row.productId }
                p?.let {
                    CartUiItem(
                        id = row.id,
                        nombre = it.nombre,
                        precio = it.precio,
                        quantity = row.quantity
                    )
                }
            }
        }
    }

    fun addToCart(clienteId: Int, productoId: Int, quantity: Int = 1) {
        viewModelScope.launch {
            cartDao.addToCart(
                CartItemEntity(
                    id = 0,
                    clienteId = clienteId,
                    productId = productoId,
                    quantity = quantity
                )
            )
            loadCart(clienteId)
        }
    }

    fun inc(clienteId: Int, item: CartUiItem) {
        viewModelScope.launch {
            cartDao.updateQuantity(item.id, item.quantity + 1)
            loadCart(clienteId)
        }
    }

    fun dec(clienteId: Int, item: CartUiItem) {
        viewModelScope.launch {
            val newQ = item.quantity - 1
            if (newQ <= 0) cartDao.remove(item.id)
            else cartDao.updateQuantity(item.id, newQ)
            loadCart(clienteId)
        }
    }

    fun remove(clienteId: Int, cartItemId: Int) {
        viewModelScope.launch {
            cartDao.remove(cartItemId)
            loadCart(clienteId)
        }
    }

    fun clearCart(clienteId: Int) {
        viewModelScope.launch {
            cartDao.clear(clienteId)
            _items.value = emptyList()
        }
    }

    fun total(): Int = _items.value.sumOf { it.precio * it.quantity }
}
