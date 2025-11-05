package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriaandroid.R
import com.example.pasteleriaandroid.data.room.ProductEntity
import com.example.pasteleriaandroid.navigation.AppRoute
import com.example.pasteleriaandroid.viewmodel.CartViewModel
import com.example.pasteleriaandroid.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    nav: NavController,
    productVM: ProductViewModel = viewModel(),
    cartVM: CartViewModel = viewModel()
) {
    val productos by productVM.productos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo Mil Sabores") },

                // 👇 Aquí agregamos el botón para volver al Home
                navigationIcon = {
                    IconButton(onClick = {
                        nav.navigate(AppRoute.Home.route) {
                            popUpTo(AppRoute.Home.route) { inclusive = false }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Volver al inicio"
                        )
                    }
                },

                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        if (productos.isEmpty()) {
            Text(
                text = "Aún no hay productos registrados 🍰",
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productos) { p ->
                    ProductoCard(
                        producto = p,
                        onAddToCart = { cartVM.addProduct(p) }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductoCard(
    producto: ProductEntity,
    onAddToCart: () -> Unit
) {
    val imagenRes = when (producto.imagen) {
        "torta_de_chocolate" -> R.drawable.torta_de_chocolate
        "tarta_de_fresa" -> R.drawable.tarta_de_fresa
        "mil_hojas" -> R.drawable.mil_hojas
        else -> R.drawable.ic_launcher_foreground
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imagenRes),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Precio: $${producto.precio}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Button(
                    onClick = onAddToCart,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("Agregar al carrito")
                }
            }
        }
    }
}
