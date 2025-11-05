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
        // HOME
        composable(AppRoute.Home.route) {
            HomeScreen(nav)
        }

        // CATÁLOGO
        composable(AppRoute.Catalogo.route) {
            CatalogoScreen(nav)
        }

        // CARRITO
        composable(AppRoute.Carrito.route) {
            CarritoScreen(nav)
        }

        // REGISTRO
        composable(AppRoute.Registro.route) {
            RegistroScreen(nav)
        }

        // CLIENTES (ya que lo tienes en tu estructura)
        composable(AppRoute.Clientes.route) {
            ClientesScreen(nav)
        }

        // DETALLE (con argumento)
        composable(AppRoute.Detalle.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(AppRoute.ARG_ID) ?: ""
            DetalleProductoScreen(nav, id)
        }
    }
}
