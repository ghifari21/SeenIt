package com.gosty.common.routes


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val activeIcon: ImageVector? = null,
    val inactiveIcon: ImageVector? = null,
) {
    object Home : Screen(
        route = "home",
        activeIcon = Icons.Filled.Home,
        inactiveIcon = Icons.Outlined.Home
    )

    object Search : Screen(
        route = "search",
        activeIcon = Icons.Filled.Search,
        inactiveIcon = Icons.Outlined.Search
    )

    object Explore : Screen(
        route = "explore",
        activeIcon = Icons.Filled.Explore,
        inactiveIcon = Icons.Outlined.Explore
    )

    object Watchlist : Screen(
        route = "watchlist",
        activeIcon = Icons.Filled.Movie,
        inactiveIcon = Icons.Outlined.Movie
    )
}