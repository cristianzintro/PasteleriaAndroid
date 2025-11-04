package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriaandroid.viewmodel.ClientesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientesScreen(nav: NavController, vm: ClientesViewModel = viewModel()) {
    val clientes by vm.clientes.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clientes registrados") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        if (clientes.isEmpty()) {
            Text(
                text = "Aún no hay clientes registrados 🧁",
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(clientes) { c ->
                    Card(
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        ListItem(
                            headlineContent = { Text(c.nombre) },
                            supportingContent = {
                                Text("${c.email}\n${c.telefono}")
                            }
                        )
                    }
                }
            }
        }
    }
}
