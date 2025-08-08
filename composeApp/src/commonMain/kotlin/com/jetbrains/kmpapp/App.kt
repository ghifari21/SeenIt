package com.jetbrains.kmpapp

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.gosty.common.ui.theme.SeenItTheme

@Composable
fun App() {
    SeenItTheme {
        Surface {
//            val navController: NavHostController = rememberNavController()
//            NavHost(navController = navController, startDestination = ListDestination) {
//                composable<ListDestination> {
//                    ListScreen(navigateToDetails = { objectId ->
//                        navController.navigate(DetailDestination(objectId))
//                    })
//                }
//                composable<DetailDestination> { backStackEntry ->
//                    DetailScreen(
//                        objectId = backStackEntry.toRoute<DetailDestination>().objectId,
//                        navigateBack = {
//                            navController.popBackStack()
//                        }
//                    )
//                }
//            }
        }
    }
}
