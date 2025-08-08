package com.gosty.common

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

actual fun platform() = "iOS"

@Composable
internal actual fun getPlatformColorScheme(
    darkTheme: Boolean,
    lightColorScheme: ColorScheme,
    darkColorScheme: ColorScheme
): ColorScheme {
    return if (darkTheme) darkColorScheme else lightColorScheme
}