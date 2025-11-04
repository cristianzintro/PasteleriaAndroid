package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
                    ProductoCardSoloImagen(
                        producto = p,
                        onAddToCart = { cartVM.addProduct(p) }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductoCardSoloImagen(
    producto: ProductEntity,
    onAddToCart: () -> Unit
) {
    // Por ahora usamos una imagen segura
    val imagenRes = R.drawable.ic_launcher_foreground

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White   // Fondo suave
        ),
        elevation = CardDefaults.cardElevation(2.dp) // Sombra ligera
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

            Button(
                onClick = onAddToCart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
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
