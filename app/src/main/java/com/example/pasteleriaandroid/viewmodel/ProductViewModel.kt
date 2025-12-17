package com.example.pasteleriaandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriaandroid.data.room.DatabaseModule
import com.example.pasteleriaandroid.data.room.ProductEntity
import com.example.pasteleriaandroid.model.Producto
import com.example.pasteleriaandroid.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    private val _productoDetalle = MutableStateFlow<Producto?>(null)
    val productoDetalle: StateFlow<Producto?> = _productoDetalle

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            // ✅ Todo lo de BD pesado a IO
            withContext(Dispatchers.IO) {
                repository.seedIfEmpty()
            }

            // ✅ Room Flow se puede colectar en Main, pero si quieres ultra-seguro:
            repository.getProductos().collectLatest { lista ->
                _productos.value = lista
            }
        }
    }

    fun cargarProductosRemotos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                // ✅ Red/API a IO
                val lista = withContext(Dispatchers.IO) {
                    repository.getRemoteProductos()
                }
                _remoteProductos.value = lista
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Error al cargar productos desde el servidor"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun cargarProductoPorId(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val p = withContext(Dispatchers.IO) {
                    repository.getRemoteProductoById(id)
                }
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
