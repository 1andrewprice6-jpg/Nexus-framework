package com.nexus.framework.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val NexusDarkScheme = darkColorScheme(
    primary = NexusCyan80, secondary = NexusGreen80, tertiary = NexusRed80,
    background = DarkNexusBackground, surface = DarkNexusSurface, surfaceVariant = DarkNexusSurfaceVariant,
    onPrimary = NexusCyan20, onBackground = NexusSurface, onSurface = NexusSurface,
)
private val NexusLightScheme = lightColorScheme(
    primary = NexusCyan40, secondary = NexusGreen40, tertiary = NexusRed40,
    background = NexusBackground, surface = NexusSurface, surfaceVariant = NexusSurfaceVariant,
    onPrimary = NexusSurface, onBackground = NexusCyan20, onSurface = NexusCyan20,
)

@Composable
fun NexusFrameworkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> NexusDarkScheme
        else -> NexusLightScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}