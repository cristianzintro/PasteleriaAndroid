package com.example.pasteleriaandroid.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriaandroid.data.session.SessionManager
import com.example.pasteleriaandroid.navigation.AppRoute
import com.example.pasteleriaandroid.viewmodel.RegistroViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(nav: NavController, vm: RegistroViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Registro de cliente") },
                navigationIcon = {
                    IconButton(onClick = {
                        nav.navigate(AppRoute.Home.route) {
                            popUpTo(AppRoute.Home.route) { inclusive = false }
                        }
                    }) {
                        Icon(Icons.Filled.Home, contentDescription = "Volver al inicio")
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CampoTexto("Nombre completo", state.nombre, vm::onNombre, state.errores["nombre"])
            CampoTexto("Correo electrónico", state.email, vm::onEmail, state.errores["email"], KeyboardType.Email)
            CampoTexto("Teléfono", state.telefono, vm::onTelefono, state.errores["telefono"], KeyboardType.Number)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Checkbox(
                    checked = state.aceptoTerminos,
                    onCheckedChange = vm::onTerminos
                )
                Text("Acepto términos y condiciones")
            }

            Button(
                onClick = {
                    vm.enviar { clienteIdCreado ->
                        SessionManager.setClienteId(clienteIdCreado)

                        scope.launch {
                            snackbarHostState.showSnackbar("¡Cuenta creada! Cliente #$clienteIdCreado ✅")
                        }

                        nav.navigate(AppRoute.Home.route) {
                            popUpTo(AppRoute.Home.route) { inclusive = true }
                        }
                    }
                },
                enabled = state.esValido,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Crear cuenta")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CampoTexto(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: String?,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            isError = error != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth()
        )

        AnimatedVisibility(visible = error != null, enter = fadeIn(), exit = fadeOut()) {
            Text(text = error ?: "", color = MaterialTheme.colorScheme.error)
        }
    }
}
