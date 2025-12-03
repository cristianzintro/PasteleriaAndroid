package com.example.pasteleriaandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pasteleriaandroid.ui.screens.*

// OJO: solo hay UNA función AppNavGraph
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
        composable(AppRoute.Carrito.route + "/{clienteId}") { backStackEntry ->
            val clienteId = backStackEntry.arguments
                ?.getString("clienteId")
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

        // DETALLE (con argumento id)
        composable(AppRoute.Detalle.route + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            DetalleProductoScreen(nav, id)
        }


        }
    }

