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
import com.example.pasteleriaandroid.navigation.AppRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(nav: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // circulito rosa con icono de torta (fake por ahora)
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFB6C8)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🍰", fontSize = 18.sp)
                        }
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

            // --- Chip de bienvenida ---
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFFF7FB0))
                    .padding(horizontal = 18.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "¡Celebra la dulzura de la vida!",
                    color = Color.White,
                    fontSize = 13.sp
                )
            }

            // --- Título grande ---
            Text(
                text = "Pastelería 1000 Sabores",
                color = Color(0xFF5A3A2E),
                fontSize = 30.sp,
                lineHeight = 34.sp,
                fontWeight = FontWeight.Bold
            )

            // --- Descripción ---
            Text(
                text = "✨ Con más de 50 años de tradición familiar, seguimos endulzando la vida de quienes nos acompañan.",
                color = Color(0xFF5A3A2E),
                fontSize = 14.sp
            )

            // --- Botones principales ---
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { nav.navigate(AppRoute.Catalogo.route) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFAC5A29),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Ver catálogo")
                }
                OutlinedButton(
                    onClick = { /* descuentos o futura pantalla */ },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF5A3A2E)
                    )
                ) {
                    Text("Activar descuentos")
                }
            }

            // --- Card de producto estrella (placeholder) ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = "Emblema de la casa",
                        color = Color(0xFF5A3A2E),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Paleta crema, rosa y chocolate.",
                        color = Color(0xFF5A3A2E),
                        fontSize = 13.sp
                    )
                }
            }

            // --- Misión y visión ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Column(Modifier.padding(14.dp)) {
                        Text(
                            text = "Misión",
                            color = Color(0xFF5A3A2E),
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Creamos momentos dulces y memorables con productos artesanales.",
                            color = Color(0xFF5A3A2E),
                            fontSize = 12.sp
                        )
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Column(Modifier.padding(14.dp)) {
                        Text(
                            text = "Visión",
                            color = Color(0xFF5A3A2E),
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Ser la pastelería de referencia para las familias dulceras de la región.",
                            color = Color(0xFF5A3A2E),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}