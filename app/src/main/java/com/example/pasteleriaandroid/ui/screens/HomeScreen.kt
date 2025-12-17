package com.example.pasteleriaandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pasteleriaandroid.data.session.SessionManager
import com.example.pasteleriaandroid.navigation.AppRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(nav: NavController) {

    val clienteId = SessionManager.getClienteId()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFB6C8)),
                            contentAlignment = Alignment.Center
                        ) { Text("🍰", fontSize = 18.sp) }

                        Spacer(Modifier.width(12.dp))
                        Text("Mil Sabores", fontWeight = FontWeight.SemiBold)
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Bienvenido a Pastelería Mil Sabores 🎂",
                color = Color(0xFF5A3A2E),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = if (clienteId != null) "Sesión activa: Cliente #$clienteId"
                else "Aún no tienes sesión (regístrate para comprar).",
                color = Color(0xFF5A3A2E)
            )

            Button(
                onClick = { nav.navigate(AppRoute.Catalogo.route) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFAC5A29),
                    contentColor = Color.White
                )
            ) { Text("Ver catálogo") }

            OutlinedButton(
                onClick = {
                    val id = SessionManager.getClienteId()
                    if (id == null) nav.navigate(AppRoute.Registro.route)
                    else nav.navigate(AppRoute.Carrito.createRoute(id))
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(if (clienteId == null) "Registrarse para usar carrito" else "Ir al carrito")
            }

            OutlinedButton(
                onClick = { nav.navigate(AppRoute.Registro.route) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF5A3A2E))
            ) { Text("Registrarse") }
        }
    }
}
