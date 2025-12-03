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

// Modelo que mostraremos en la UI
data class CartUiItem(
    val id: Int,
    val nombre: String,
    val precio: Int,   // 👈 ahora Int, igual que en ProductEntity
    val quantity: Int
)

class CartViewModel(app: Application) : AndroidViewModel(app) {

    private val db = DatabaseModule.getDatabase(app)
    private val cartDao = db.cartDao()
    private val productDao = db.productDao()

    private val _items = MutableStateFlow<List<CartUiItem>>(emptyList())
    val items: StateFlow<List<CartUiItem>> = _items

    /**
     * Carga el carrito de un cliente específico desde Room
     */
    fun loadCart(clienteId: Int) {
        viewModelScope.launch {
            // 1) filas del carrito
            val cartRows: List<CartItemEntity> = cartDao.getCartByCliente(clienteId)
            // 2) productos (tu dao devuelve Flow, así que tomamos el primero)
            val productos = productDao.getAll().first()

            // 3) cruzamos carrito + productos
            val uiList = cartRows.mapNotNull { cart ->
                val p = productos.find { it.id == cart.productId }
                p?.let {
                    CartUiItem(
                        id = cart.id,
                        nombre = it.nombre,
                        precio = it.precio,      // 👈 ahora coincide el tipo (Int)
                        quantity = cart.quantity
                    )
                }
            }

            _items.value = uiList
        }
    }

    /**
     * Vaciar carrito de ese cliente
     */
    fun clearCart(clienteId: Int) {
        viewModelScope.launch {
            cartDao.clear(clienteId)
            _items.value = emptyList()
        }
    }

    /**
     * Total calculado
     */
    fun total(): Double =
        _items.value.sumOf { it.precio.toDouble() * it.quantity }
}
