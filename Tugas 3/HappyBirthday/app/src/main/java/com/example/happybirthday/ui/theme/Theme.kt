package com.example.selamatulangtahun.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CustomColorScheme = lightColorScheme(
    primary = Color(0xFF3F51B5), // warna ungu terang
    background = Color(0xFFF0F0F0),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF333333)
)

@Composable
fun HappyBirthdayTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CustomColorScheme,
        typography = Typography(),
        content = content
    )
}
