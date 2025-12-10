package com.example.pasteleriaandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.pasteleriaandroid.ui.screens.HomeScreen
import com.example.pasteleriaandroid.ui.screens.CatalogoScreen
import com.example.pasteleriaandroid.ui.screens.CarritoScreen
import com.example.pasteleriaandroid.ui.screens.RegistroScreen
import com.example.pasteleriaandroid.ui.screens.ClientesScreen
import com.example.pasteleriaandroid.ui.screens.DetalleProductoScreen

@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(
        navController = nav,
        startDestination = AppRoute.Home.route
    ) {
        // HOME
        composable(AppRoute.Home.route) {
            HomeScreen(nav)
        }

        // CATÁLOGO
        composable(AppRoute.Catalogo.route) {
            CatalogoScreen(nav)
        }

        // 🔹 CARRITO SIN PARÁMETRO (usa cliente 1 por defecto)
        composable(AppRoute.Carrito.route) {
            CarritoScreen(
                nav = nav,
                clienteId = 1   // puedes cambiarlo cuando tengas login
            )
        }

        // 🔹 CARRITO CON PARÁMETRO: carrito/{clienteId}
        composable(AppRoute.Carrito.route + "/{${AppRoute.ARG_CLIENTE_ID}}") { backStackEntry ->
            val clienteId = backStackEntry.arguments
                ?.getString(AppRoute.ARG_CLIENTE_ID)
                ?.toIntOrNull()
                ?: 1

            CarritoScreen(
                nav = nav,
                clienteId = clienteId
            )
        }

        // REGISTRO
        composable(AppRoute.Registro.route) {
            RegistroScreen(nav)
        }

        // CLIENTES
        composable(AppRoute.Clientes.route) {
            ClientesScreen(nav)
        }

        // 🔹 DETALLE PRODUCTO: detalle/{id}
        composable(
            route = AppRoute.DetalleProducto.route,
            arguments = listOf(
                navArgument(AppRoute.ARG_ID) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(AppRoute.ARG_ID) ?: 0
            DetalleProductoScreen(
                nav = nav,
                productoId = id
            )
        }
    }
}
