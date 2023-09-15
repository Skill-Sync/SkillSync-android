package com.ss.skillsync.commonandroid.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private var DarkColorScheme = darkColorScheme(
    primary = Blue,
    secondary = Yellow,
    tertiary = Blue,
    background = Shark,
    onBackground = White,
    outline = Scorpion,
    surface = LightBlack,
    onSurface = White,
    primaryContainer = LightBlue10,
    secondaryContainer = LightBlue20,
    onPrimaryContainer = RibbonBlue,
    onSecondaryContainer = LightBlue,
    error = ErrorRed
)

@Composable
fun SkillSyncTheme(
    backgroundColor: Color = DarkColorScheme.background,
    content: @Composable () -> Unit,
) {
    DarkColorScheme = DarkColorScheme.copy(background = backgroundColor)

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DarkColorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
