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

// Modelo mostrado en la UI
data class CartUiItem(
    val id: Int,
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

    /**
     * Cargar productos del carrito desde Room (por cliente)
     */
    fun loadCart(clienteId: Int) {
        viewModelScope.launch {
            val cartRows: List<CartItemEntity> = cartDao.getCartByCliente(clienteId)
            val productos = productDao.getAll().first()

            val uiList = cartRows.mapNotNull { cart ->
                val p = productos.find { it.id == cart.productId }
                p?.let {
                    CartUiItem(
                        id = cart.id,
                        nombre = it.nombre,
                        precio = it.precio,
                        quantity = cart.quantity
                    )
                }
            }

            _items.value = uiList
        }
    }

    /**
     * AGREGAR PRODUCTO AL CARRITO
     */
    fun addToCart(clienteId: Int, productoId: Int, quantity: Int = 1) {
        viewModelScope.launch {

            val item = CartItemEntity(
                id = 0,             // autogenerado por Room
                clienteId = clienteId,
                productId = productoId,
                quantity = quantity
            )

            cartDao.addToCart(item)  // <-- AQUÍ EL AJUSTE

            // Refrescar el carrito
            loadCart(clienteId)
        }
    }

    /**
     * Vaciar carrito
     */
    fun clearCart(clienteId: Int) {
        viewModelScope.launch {
            cartDao.clear(clienteId)
            _items.value = emptyList()
        }
    }

    /**
     * Total del carrito
     */
    fun total(): Double =
        _items.value.sumOf { it.precio.toDouble() * it.quantity }
}
