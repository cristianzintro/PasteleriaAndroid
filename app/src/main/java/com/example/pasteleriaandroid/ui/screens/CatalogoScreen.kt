package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Modelo básico para productos del catálogo
data class ProductoUi(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Int
)

// Lista de 3 productos (tortas)
private val productosCatalogo = listOf(
    ProductoUi(
        id = 1,
        nombre = "Torta Tres Leches",
        descripcion = "Bizcocho húmedo con mezcla de tres leches y cubierta de merengue.",
        precio = 15000
    ),
    ProductoUi(
        id = 2,
        nombre = "Torta Mil Sabores",
        descripcion = "Clásica de la casa con mezcla de frutilla, chocolate y vainilla.",
        precio = 18000
    ),
    ProductoUi(
        id = 3,
        nombre = "Torta Selva Negra",
        descripcion = "Bizcochuelo de chocolate, crema batida y cerezas.",
        precio = 17000
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(nav: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Tortas") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(productosCatalogo) { producto ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = producto.nombre,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = producto.descripcion,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Precio: $${producto.precio}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
