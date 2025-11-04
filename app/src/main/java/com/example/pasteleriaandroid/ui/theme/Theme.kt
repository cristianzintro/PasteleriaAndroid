package com.example.pasteleriaandroid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    secondary = Pink40,
    onSecondary = Color.White,
    tertiary = PurpleGrey40,
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F)
)

private val DarkColors = darkColorScheme(
    primary = Purple80,
    onPrimary = Color(0xFF1C1B1F),
    secondary = Pink80,
    onSecondary = Color(0xFF1C1B1F),
    tertiary = PurpleGrey80,
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5)
)

@Composable
fun MilSaboresTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(colorScheme = if (useDarkTheme) DarkColors else LightColors, content = content)
}
