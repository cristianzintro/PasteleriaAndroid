package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriaandroid.navigation.AppRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(nav: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pastelería Mil Sabores") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { p ->
        Column(
            modifier = Modifier
                .padding(p)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 🔹 Botón para ver lista de clientes
            Button(
                onClick = { nav.navigate(AppRoute.Clientes.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver clientes")
            }

            // 🔹 BOTÓN AGREGADO (aquí está el que pediste)
            Button(
                onClick = { nav.navigate(AppRoute.Catalogo.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver catálogo")
            }

            // 🔹 Botón para ver el carrito
            Button(
                onClick = { nav.navigate(AppRoute.Carrito.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Carrito")
            }

            // 🔹 Botón para registrar nuevos clientes
            Button(
                onClick = { nav.navigate(AppRoute.Registro.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }
        }
    }
}
