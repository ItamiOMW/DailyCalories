package com.example.dailycalories.presentation.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    onPrimary = White,
    background = DarkBlue,
    onBackground = White,
    secondary = White,
    secondaryVariant = GrayWhite,
    onSecondary = Gray,
    surface = Gray,
    onSurface = White,
)

private val LightColorPalette = lightColors(
    primary = White,
    onPrimary = DarkBlue,
    background = White,
    onBackground = DarkBlue,
    secondary = DarkBlue,
    secondaryVariant = Gray,
    onSecondary = DarkBlue,
    surface = GrayWhite,
    onSurface = Gray
)

@Composable
fun DailyCaloriesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}