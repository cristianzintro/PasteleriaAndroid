package com.example.pasteleriaandroid.navigation

// Definimos todas las rutas que usa la app
enum class AppRoute(val route: String) {
    Home("home"),
    Catalogo("catalogo"),
    Carrito("carrito"),
    Registro("registro"),
    Clientes("clientes"),
    // detalle necesita un argumento
    Detalle("detalle/{id}");

    companion object {
        // nombre del argumento que usaremos en NavGraph
        const val ARG_ID = "id"

        // helper para navegar a un detalle concreto
        fun detalleDe(id: String) = "detalle/$id"
    }
}
