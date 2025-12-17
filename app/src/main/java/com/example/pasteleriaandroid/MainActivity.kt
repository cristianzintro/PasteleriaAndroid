package com.example.pasteleriaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.pasteleriaandroid.navigation.AppNavGraph
import com.example.pasteleriaandroid.ui.theme.MilSaboresTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MilSaboresTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nav = rememberNavController()
                    AppNavGraph(nav)
                }
            }
        }
    }
}
