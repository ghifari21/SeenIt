package com.jetbrains.kmpapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gosty.common.ui.components.BottomNavigation
import com.gosty.common.ui.theme.SeenItTheme

@Composable
fun App() {
    SeenItTheme {
        Surface {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { BottomNavigation(navController = navController) },
            ) { paddingValues ->
                AppNavHost(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}
