package com.example.pasteleriaandroid

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.pasteleriaandroid.navigation.AppNavGraph
import com.example.pasteleriaandroid.ui.theme.MilSaboresTheme  // <--- ESTE IMPORT ES CLAVE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MilSaboresTheme {
                // un Surface ayuda a que el fondo pastel se vea en todas las pantallas
                androidx.compose.material3.Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nav = rememberNavController()
                    AppNavGraph(nav)
                }
            }
        }

    }
    }