package com.example.pasteleriaandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pasteleriaandroid.ui.screens.*

@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(
        navController = nav,
        startDestination = AppRoute.Home.route
    ) {
        composable(AppRoute.Home.route) { HomeScreen(nav) }
        composable(AppRoute.Catalogo.route) { CatalogoScreen(nav) }
        composable(AppRoute.Registro.route) { RegistroScreen(nav) }

        // ✅ carrito con argumento SI o SI
        composable(
            route = AppRoute.Carrito.route,
            arguments = listOf(navArgument(AppRoute.ARG_CLIENTE_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val clienteId = backStackEntry.arguments?.getInt(AppRoute.ARG_CLIENTE_ID) ?: 0
            CarritoScreen(nav = nav, clienteId = clienteId)
        }

        composable(
            route = AppRoute.DetalleProducto.route,
            arguments = listOf(navArgument(AppRoute.ARG_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(AppRoute.ARG_ID) ?: 0
            DetalleProductoScreen(nav = nav, productoId = id)
        }
    }
}
