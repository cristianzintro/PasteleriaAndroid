package com.example.pasteleriaandroid.navigation

sealed class AppRoute(val route: String) {
    object Home : AppRoute("home")
    object Catalogo : AppRoute("catalogo")
    object Registro : AppRoute("registro")

    // ✅ siempre con clienteId
    object Carrito : AppRoute("carrito/{clienteId}") {
        fun createRoute(clienteId: Int) = "carrito/$clienteId"
    }

    object DetalleProducto : AppRoute("detalle/{id}") {
        fun createRoute(id: Int) = "detalle/$id"
    }

    companion object {
        const val ARG_ID = "id"
        const val ARG_CLIENTE_ID = "clienteId"
    }
}
