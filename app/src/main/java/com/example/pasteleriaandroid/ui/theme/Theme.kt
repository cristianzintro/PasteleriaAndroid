package com.example.pasteleriaandroid.ui.theme

import androidx.compose.ui.graphics.toArgb 
import androidx.compose.ui.graphics.Color
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = PinkPrimary,
    onPrimary = Color.White,
    primaryContainer = PinkPastel,
    onPrimaryContainer = ChocoText,

    secondary = MintAccent,
    onSecondary = ChocoText,

    background = CreamBackground,
    onBackground = ChocoText,

    surface = Color.White,
    onSurface = ChocoText,

    error = Color(0xFFB3261E)
)

@Composable
fun MilSaboresTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // vamos a forzar claro porque es una pastelería
    val colorScheme = LightColors

    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        window.statusBarColor = colorScheme.primary.toArgb()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
