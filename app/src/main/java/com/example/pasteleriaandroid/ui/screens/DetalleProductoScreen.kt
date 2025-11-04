package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleProductoScreen(nav: NavController, id: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Detalle del producto") }) }
    ) { padding ->
        Text(
            text = "Mostrando detalles del producto con ID: $id 🍮",
            modifier = Modifier.padding(padding).padding(16.dp)
        )
    }
}
