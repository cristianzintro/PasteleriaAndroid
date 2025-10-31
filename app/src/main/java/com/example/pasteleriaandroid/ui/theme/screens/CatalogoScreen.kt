package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriaandroid.viewmodel.ProductViewModel

@Composable
fun CatalogoScreen(nav: NavController, vm: ProductViewModel = viewModel()) {
    LaunchedEffect(Unit) { vm.seedData() }

    Scaffold(topBar = { TopAppBar(title = { Text("Catálogo") }) }) { padding ->
        val productos = vm.productos.collectAsState()

        LazyColumn(Modifier.padding(padding).padding(16.dp)) {
            items(productos.value) { p ->
                Card(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Text(p.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("${p.precio} CLP", style = MaterialTheme.typography.bodyMedium)
                        Text(p.descripcion, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
