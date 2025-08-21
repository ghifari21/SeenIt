package com.gosty.seenit

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gosty.common.routes.Screen
import com.gosty.common.ui.components.BottomNavigation
import com.gosty.common.ui.theme.SeenItTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import seenit.composeapp.generated.resources.Res
import seenit.composeapp.generated.resources.home
import seenit.composeapp.generated.resources.search
import seenit.composeapp.generated.resources.watchlist

@Composable
fun App() {
    SeenItTheme {
        Surface {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomNavigation(
                        navController = navController,
                        labels = mapOf(
                            Screen.Home.route to stringResource(Res.string.home),
                            Screen.Search.route to stringResource(Res.string.search),
                            Screen.Watchlist.route to stringResource(Res.string.watchlist)
                        )
                    )
                },
            ) { paddingValues ->
                AppNavHost(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}