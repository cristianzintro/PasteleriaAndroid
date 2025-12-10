package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.DatabaseModule
import com.example.pasteleriaandroid.data.room.ProductEntity
import com.example.pasteleriaandroid.model.Producto
import com.example.pasteleriaandroid.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductViewModel(app: Application) : AndroidViewModel(app) {

    private val db = DatabaseModule.getDatabase(app)
    private val productDao = db.productDao()
    private val repository = ProductRepository(productDao)

    // ==========================
    // 🟣 PARTE LOCAL (ROOM)
    // ==========================
    private val _productos = MutableStateFlow<List<ProductEntity>>(emptyList())
    val productos: StateFlow<List<ProductEntity>> = _productos

    // ==========================
    // 🟣 PARTE REMOTA (API)
    // ==========================
    private val _remoteProductos = MutableStateFlow<List<Producto>>(emptyList())
    val remoteProductos: StateFlow<List<Producto>> = _remoteProductos

    // Detalle de un producto remoto
    private val _productoDetalle = MutableStateFlow<Producto?>(null)
    val productoDetalle: StateFlow<Producto?> = _productoDetalle

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            // llena la tabla local si está vacía
            repository.seedIfEmpty()

            // escucha cambios de Room
            repository.getProductos().collectLatest { lista ->
                _productos.value = lista
            }
        }
    }

    // Lista remota para el catálogo
    fun cargarProductosRemotos() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val lista = repository.getRemoteProductos()
                _remoteProductos.value = lista
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Error al cargar productos desde el servidor"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Detalle remoto por id
    fun cargarProductoPorId(id: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val p = repository.getRemoteProductoById(id)
                _productoDetalle.value = p
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "No se pudo cargar el producto"
                _productoDetalle.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}
