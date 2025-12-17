package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pasteleriaandroid.data.session.SessionManager
import com.example.pasteleriaandroid.navigation.AppRoute
import com.example.pasteleriaandroid.viewmodel.CartViewModel
import com.example.pasteleriaandroid.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleProductoScreen(
    nav: NavController,
    productoId: Int,
    productVm: ProductViewModel = viewModel(),
    cartVm: CartViewModel = viewModel()
) {
    val producto by productVm.productoDetalle.collectAsState()
    val isLoading by productVm.isLoading.collectAsState()
    val error by productVm.error.collectAsState()

    val clienteId = SessionManager.getClienteId() ?: 0

    LaunchedEffect(productoId) {
        productVm.cargarProductoPorId(productoId)
    }

    val fondo = MaterialTheme.colorScheme.surfaceVariant

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del producto") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(fondo)
        ) {
            when {
                isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))

                error != null -> Text(
                    text = error ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
                    textAlign = TextAlign.Center
                )

                producto == null -> Text(
                    text = "Producto no encontrado",
                    modifier = Modifier.align(Alignment.Center).padding(16.dp),
                    textAlign = TextAlign.Center
                )

                else -> {
                    DetalleProductoContent(
                        nombre = producto!!.nombre,
                        descripcion = producto!!.descripcion ?: "",
                        precio = producto!!.precio,
                        imagenUrl = producto!!.imagen,
                        categoria = producto!!.categoria,
                        codigo = producto!!.codigo,
                        onAgregarCarrito = {
                            if (clienteId == 0) {
                                nav.navigate(AppRoute.Registro.route)
                            } else {
                                cartVm.addToCart(clienteId, producto!!.id, 1)
                                nav.navigate(AppRoute.Carrito.createRoute(clienteId))
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DetalleProductoContent(
    nombre: String,
    descripcion: String,
    precio: Int,
    imagenUrl: String,
    categoria: String?,
    codigo: String?,
    onAgregarCarrito: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .shadow(6.dp, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp)
        ) {
            AsyncImage(
                model = imagenUrl,
                contentDescription = nombre,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(24.dp))
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(nombre, fontSize = 22.sp, fontWeight = FontWeight.Bold)

        categoria?.let {
            Text("Categoría: $it", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        }

        codigo?.let {
            Text("Código: $it", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }

        Spacer(Modifier.height(12.dp))

        Text("Descripción", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Text(descripcion, fontSize = 14.sp, modifier = Modifier.padding(top = 4.dp))

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Precio: $$precio",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(24.dp))

        Button(onClick = onAgregarCarrito, modifier = Modifier.fillMaxWidth()) {
            Text("Agregar al carrito")
        }
    }
}
