package com.example.pasteleriaandroid.repository

import com.example.pasteleriaandroid.data.remote.ApiService
import com.example.pasteleriaandroid.data.remote.RetrofitInstance
import com.example.pasteleriaandroid.data.room.ProductDao
import com.example.pasteleriaandroid.data.room.ProductEntity
import com.example.pasteleriaandroid.model.Producto

class ProductRepository(
    private val dao: ProductDao,
    // 👇 por defecto usamos el ApiService de RetrofitInstance
    private val api: ApiService = RetrofitInstance.api
) {

    // ======================
    // 🟣 PARTE LOCAL (ROOM)
    // ======================

    // Lo que ya tenías: sigue igual
    fun getProductos() = dao.getAll()

    suspend fun seedIfEmpty() {
        val count = dao.count()
        if (count == 0) {
            dao.insertAll(
                listOf(
                    ProductEntity(
                        nombre = "Torta Cuadrada de Chocolate",
                        descripcion = "Deliciosa torta de chocolate con ganache y avellanas, perfecta para celebraciones especiales.",
                        precio = 45000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/torta-cuadrada-de-chocolate.jpg"
                    ),
                    ProductEntity(
                        nombre = "Torta Redonda de Frutilla",
                        descripcion = "Bizcocho esponjoso con crema y frutillas frescas, ideal para eventos y cumpleaños.",
                        precio = 42000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/torta_redonda_de_frutilla.jpg"
                    ),
                    ProductEntity(
                        nombre = "Cheesecake de Maracuyá",
                        descripcion = "Cheesecake cremoso con salsa de maracuyá natural y base crocante.",
                        precio = 48000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/torta_redonda_de_frutilla.jpg"
                    ),
                    ProductEntity(
                        nombre = "Torta de Frambuesa y Crema",
                        descripcion = "Torta fresca con crema batida y frambuesas naturales, un sabor único.",
                        precio = 43000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/torta_de_frambuesa_y_crema.jpg"
                    ),
                    ProductEntity(
                        nombre = "Torta Oreo",
                        descripcion = "Bizcocho de chocolate con crema Oreo y topping crocante, una favorita de los niños.",
                        precio = 47000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/torta_de_frambuesa_y_crema.jpg"
                    ),
                    ProductEntity(
                        nombre = "Torta Tres Leches",
                        descripcion = "La clásica torta húmeda bañada en tres leches y decorada con merengue.",
                        precio = 39000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/tortas_tres_leches.png"
                    ),
                    ProductEntity(
                        nombre = "Torta Selva Negra",
                        descripcion = "Bizcocho de chocolate con crema chantilly y cerezas, receta tradicional alemana.",
                        precio = 52000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/torta_selva_negra.png"
                    ),
                    ProductEntity(
                        nombre = "Pie de Limón",
                        descripcion = "Clásico pie de limón con merengue tostado y base de galleta.",
                        precio = 35000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/pie_de_limon.png"
                    ),
                    ProductEntity(
                        nombre = "Torta Tiramisú",
                        descripcion = "El tradicional tiramisú italiano con café, cacao y queso mascarpone.",
                        precio = 51000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/torta_tiramisu.png"
                    ),
                    ProductEntity(
                        nombre = "Torta de Vainilla con Buttercream",
                        descripcion = "Bizcocho suave de vainilla cubierto con buttercream casero.",
                        precio = 41000,
                        imagen = "https://raw.githubusercontent.com/cristianzintro/Imagenes_Catalogo_milSabores/refs/heads/main/ImagenesCatalogo/torta_de_vainilla_con_buttercream.png"
                    )
                )
            )
        }
    }

    // ======================
    // 🟣 PARTE REMOTA (API)
    // ======================

    // Productos desde Spring Boot (tabla conectada a XAMPP)
    suspend fun getRemoteProductos(): List<Producto> {
        return api.getProductos()
    }

    suspend fun getRemoteProductoById(id: Int): Producto {
        return api.getProductoById(id)
    }
}
