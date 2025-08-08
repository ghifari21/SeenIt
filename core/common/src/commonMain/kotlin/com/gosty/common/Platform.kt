package com.gosty.common

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

expect fun platform(): String

@Composable
internal expect fun getPlatformColorScheme(
    darkTheme: Boolean,
    lightColorScheme: ColorScheme,
    darkColorScheme: ColorScheme
): ColorScheme