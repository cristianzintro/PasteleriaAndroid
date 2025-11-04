package com.example.pasteleriaandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pasteleriaandroid.ui.screens.*

@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(
        navController = nav,
        startDestination = AppRoute.Home.route
    ) {
        composable(AppRoute.Home.route) { HomeScreen(nav) }
        composable(AppRoute.Catalogo.route) { CatalogoScreen(nav) }

        // 🔹 Pantalla Carrito
        composable(AppRoute.Carrito.route) {
            // Si CarritoScreen no existe aún, esto evita errores de compilación
            CarritoScreen(nav)
        }

        // 🔹 Pantalla Registro
        composable(AppRoute.Registro.route) {
            RegistroScreen(nav)
        }
        composable(AppRoute.Clientes.route) { ClientesScreen(nav) }


        // 🔹 Pantalla Detalle con argumento "id"
        composable(AppRoute.Detalle.route) { backStack ->
            val id = backStack.arguments?.getString(AppRoute.Detalle.ARG_ID) ?: ""
            DetalleProductoScreen(nav, id)
        }
    }
}
