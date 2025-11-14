package com.example.pasteleriaandroid.navigation

sealed class AppRoute(val route: String) {

    // Rutas que ya usas
    object Home : AppRoute("home")
    object Catalogo : AppRoute("catalogo")
    object Carrito : AppRoute("carrito")
    object Registro : AppRoute("registro")
    object Clientes : AppRoute("clientes")
    object Detalle : AppRoute("detalle")

    // 👇 NUEVA RUTA PARA LA API
    object Posts : AppRoute("posts")

    // Constantes para argumentos (si las quieres usar)
    companion object {
        const val ARG_ID = "id"
        const val ARG_CLIENTE_ID = "clienteId"
    }
}
