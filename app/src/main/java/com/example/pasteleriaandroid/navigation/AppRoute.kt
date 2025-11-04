package com.example.pasteleriaandroid.navigation

sealed class AppRoute(val route: String) {
    data object Home : AppRoute("home")
    data object Catalogo : AppRoute("catalogo")
    data object Carrito : AppRoute("carrito")
    data object Registro : AppRoute("registro")

    data object Clientes : AppRoute("clientes")

    data object Detalle : AppRoute("detalle/{id}") { const val ARG_ID = "id" }
}
