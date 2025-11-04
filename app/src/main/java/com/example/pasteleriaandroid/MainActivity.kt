package com.example.pasteleriaandroid

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
            MilSaboresTheme {  // <--- Asegúrate que diga MilSaboresTheme, no PasteleriaAndroidTheme
                val nav = rememberNavController()
                AppNavGraph(nav)
            }
        }
    }
}
