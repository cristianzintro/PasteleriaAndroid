package com.example.pasteleriaandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.pasteleriaandroid.navigation.AppNavGraph
import com.example.pasteleriaandroid.ui.theme.PasteleriaAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasteleriaAndroidTheme {
                val nav = rememberNavController()
                AppNavGraph(nav)
            }
        }
    }
}
