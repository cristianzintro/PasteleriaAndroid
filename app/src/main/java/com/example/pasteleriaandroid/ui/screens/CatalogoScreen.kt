package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pasteleriaandroid.data.room.ProductEntity
import com.example.pasteleriaandroid.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    nav: NavController,
    vm: ProductViewModel = viewModel()
) {
    val productos by vm.productos.collectAsState()

    var query by remember { mutableStateOf("") }

    val productosFiltrados = remember(query, productos) {
        if (query.isBlank()) productos
        else productos.filter { it.nombre.contains(query, ignoreCase = true) }
    }

    val fondoCrema = MaterialTheme.colorScheme.surfaceVariant
    val bannerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
    val textoPrincipal = MaterialTheme.colorScheme.onSurface

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Catálogo",
                        color = textoPrincipal,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        containerColor = fondoCrema
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(fondoCrema)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            // Banner "Catálogo de Productos"
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .shadow(4.dp, RoundedCornerShape(24.dp))
                    .background(bannerColor, RoundedCornerShape(24.dp))
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Catálogo de Productos",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textoPrincipal
                )
            }

            Text(
                text = "Explora nuestras tortas, pasteles y productos especiales.",
                fontSize = 14.sp,
                color = textoPrincipal,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Buscar"
                    )
                },
                placeholder = { Text("Búsqueda...") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productosFiltrados, key = { it.id }) { producto ->
                    ProductRow(producto)
                }
            }
        }
    }
}

@Composable
private fun ProductRow(producto: ProductEntity) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen desde URL (GitHub RAW o lo que uses)
            Image(
                painter = rememberAsyncImagePainter(model = producto.imagen),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = producto.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                // si no tienes categoria en la entidad, puedes quitar esta parte
                Text(
                    text = producto.descripcion,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$${producto.precio}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
