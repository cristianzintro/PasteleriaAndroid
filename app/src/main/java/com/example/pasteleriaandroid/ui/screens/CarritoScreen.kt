package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriaandroid.navigation.AppRoute
import com.example.pasteleriaandroid.viewmodel.CartViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    nav: NavController,
    clienteId: Int,
    vm: CartViewModel = viewModel()
) {
    LaunchedEffect(clienteId) { vm.loadCart(clienteId) }

    val items by vm.items.collectAsState()
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbar) },
        topBar = {
            TopAppBar(
                title = { Text("Carrito de compras") },
                navigationIcon = {
                    IconButton(onClick = { nav.navigate(AppRoute.Home.route) }) {
                        Icon(Icons.Filled.Home, contentDescription = "Inicio")
                    }
                },
                colors = topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (items.isEmpty()) {
                Text("Tu carrito está vacío 🧁")
                Spacer(Modifier.height(12.dp))
                Button(onClick = { nav.navigate(AppRoute.Catalogo.route) }) {
                    Text("Ir al catálogo")
                }
            } else {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items) { p ->
                        Card {
                            Column(Modifier.padding(12.dp)) {
                                Text(p.nombre, style = MaterialTheme.typography.titleMedium)
                                Text("Precio: $${p.precio}")
                                Spacer(Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row {
                                        IconButton(onClick = { vm.dec(clienteId, p) }) {
                                            Icon(Icons.Filled.Remove, contentDescription = "Menos")
                                        }
                                        Text(" ${p.quantity} ")
                                        IconButton(onClick = { vm.inc(clienteId, p) }) {
                                            Icon(Icons.Filled.Add, contentDescription = "Más")
                                        }
                                    }

                                    IconButton(onClick = {
                                        vm.remove(clienteId, p.id)
                                        scope.launch { snackbar.showSnackbar("Producto eliminado") }
                                    }) {
                                        Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "Total: $${vm.total()}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        vm.clearCart(clienteId)
                        scope.launch { snackbar.showSnackbar("Carrito vaciado") }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Vaciar carrito")
                }
            }
        }
    }
}
