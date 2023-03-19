package com.example.dailycalories.presentation.theme.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = White,
    primaryVariant = Gray,
    onPrimary = DarkBlue,
    background = DarkBlue,
    onBackground = White,
    secondary = Purple,
    secondaryVariant = Gray,
    onSecondary = White,
    surface = Gray,
    onSurface = White,
)

private val LightColorPalette = lightColors(
    primary = DarkBlue,
    primaryVariant = GrayLight,
    onPrimary = White,
    background = White,
    onBackground = DarkBlue,
    secondary = Purple,
    secondaryVariant = Gray,
    onSecondary = White,
    surface = WhiteDark,
    onSurface = DarkBlue
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