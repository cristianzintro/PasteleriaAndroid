package com.example.pasteleriaandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.pasteleriaandroid.ui.screens.*

@Composable
fun AppNavGraph(nav: NavHostController) {
    NavHost(navController = nav, startDestination = AppRoute.Home.route) {
        composable(AppRoute.Home.route) { HomeScreen(nav) }
        composable(AppRoute.Catalogo.route) { CatalogoScreen(nav) }
        composable(AppRoute.Carrito.route) { CarritoScreen(nav) }
        composable(AppRoute.Registro.route) { RegistroScreen(nav) }
        composable(AppRoute.Detalle.route) { backStack ->
            val id = backStack.arguments?.getString(AppRoute.Detalle.ARG_ID) ?: ""
            DetalleProductoScreen(nav, id)
        }
    }
}
