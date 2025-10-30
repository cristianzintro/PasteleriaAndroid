package com.example.pasteleriaandroid.ui.theme.screens

package com.example.pasteleriaandroid.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(nav: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Pastelería Mil Sabores") }) }
    ) { p ->
        Column(Modifier.padding(p).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { nav.navigate(AppRoute.Catalogo.route) }) { Text("Ver catálogo") }
            Button(onClick = { nav.navigate(AppRoute.Carrito.route) }) { Text("Carrito") }
            Button(onClick = { nav.navigate(AppRoute.Registro.route) }) { Text("Registrarse") }
        }
    }
}
