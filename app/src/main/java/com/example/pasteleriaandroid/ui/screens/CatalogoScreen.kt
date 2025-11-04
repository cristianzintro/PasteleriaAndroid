package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriaandroid.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(nav: NavController, vm: ProductViewModel = viewModel()) {
    LaunchedEffect(Unit) { vm.seedData() }

    Scaffold(topBar = { TopAppBar(title = { Text("Catálogo") }) }) { p ->
        val productos by vm.productos.collectAsState()
        LazyColumn(Modifier.padding(p).padding(16.dp)) {
            items(productos) { prod ->
                Card(Modifier.padding(bottom = 12.dp)) {
                    ListItem(
                        headlineContent = { Text(prod.nombre) },
                        supportingContent = { Text(prod.descripcion) },
                        trailingContent = { Text("${prod.precio} CLP") }
                    )
                }
            }
        }
    }
}
