package com.example.pasteleriaandroid.navigation

sealed class AppRoute(val route: String) {

    // Rutas principales
    object Home : AppRoute("home")
    object Catalogo : AppRoute("catalogo")
    object Carrito : AppRoute("carrito")
    object Registro : AppRoute("registro")
    object Clientes : AppRoute("clientes")

    // 🔹 Detalle de producto con parámetro {id}
    object DetalleProducto : AppRoute("detalle/{id}") {
        fun createRoute(id: Int) = "detalle/$id"
    }

    // (Opcional) Ruta para posts de API externa
    object Posts : AppRoute("posts")

    companion object {
        const val ARG_ID = "id"
        const val ARG_CLIENTE_ID = "clienteId"
    }
}
