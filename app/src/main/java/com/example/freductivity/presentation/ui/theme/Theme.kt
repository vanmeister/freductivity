package com.example.freductivity.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Indigo80,
    onPrimary = Indigo20,
    primaryContainer = Indigo30,
    onPrimaryContainer = Indigo90,

    background = Neutral6,
    onBackground = Neutral90,
    surface = Neutral6,
    onSurface = Neutral90,
    onSurfaceVariant = NeutralVariant70,
    surfaceContainerLow = Neutral10,
    surfaceContainerHigh = Neutral17,
    surfaceContainerHighest = Neutral22,

    outline = NeutralVariant60,
    outlineVariant = NeutralVariant30,

    error = Red80,
    onError = Red20,

    scrim = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Indigo40,
    onPrimary = Color.White,
    primaryContainer = Indigo90,
    onPrimaryContainer = Indigo15,

    background = Neutral98,
    onBackground = Neutral10,
    surface = Neutral98,
    onSurface = Neutral10,
    onSurfaceVariant = NeutralVariant35,
    surfaceContainerLow = Neutral95,
    surfaceContainerHigh = Neutral91,
    surfaceContainerHighest = Neutral86,

    outline = NeutralVariant47,
    outlineVariant = NeutralVariant80,

    error = Red40,
    onError = Color.White,

    scrim = Color.Black,
)

@Composable
fun FreductivityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}