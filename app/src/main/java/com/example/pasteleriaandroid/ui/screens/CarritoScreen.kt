package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(nav: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Carrito") }) }) { padding ->
        Text(
            text = "Tu carrito está vacío 🍰",
            modifier = Modifier.padding(padding).padding(16.dp)
        )
    }
}
