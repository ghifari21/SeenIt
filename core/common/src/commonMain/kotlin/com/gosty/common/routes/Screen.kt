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
import org.jetbrains.compose.resources.StringResource
import seenit.core.common.generated.resources.Res
import seenit.core.common.generated.resources.explore
import seenit.core.common.generated.resources.home
import seenit.core.common.generated.resources.search
import seenit.core.common.generated.resources.watchlist

sealed class Screen(
    val route: String,
    val resource: StringResource? = null,
    val activeIcon: ImageVector? = null,
    val inactiveIcon: ImageVector? = null,
) {
    object Home : Screen(
        route = "home",
        resource = Res.string.home,
        activeIcon = Icons.Filled.Home,
        inactiveIcon = Icons.Outlined.Home
    )

    object Search : Screen(
        route = "search",
        resource = Res.string.search,
        activeIcon = Icons.Filled.Search,
        inactiveIcon = Icons.Outlined.Search
    )

    object Explore : Screen(
        route = "explore",
        resource = Res.string.explore,
        activeIcon = Icons.Filled.Explore,
        inactiveIcon = Icons.Outlined.Explore
    )

    object Watchlist : Screen(
        route = "watchlist",
        resource = Res.string.watchlist,
        activeIcon = Icons.Filled.Movie,
        inactiveIcon = Icons.Outlined.Movie
    )
}