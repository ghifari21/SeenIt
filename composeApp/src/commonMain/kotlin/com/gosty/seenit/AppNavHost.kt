package com.gosty.seenit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gosty.common.routes.Screen
import com.gosty.home.ui.screens.HomeScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import seenit.composeapp.generated.resources.Res
import seenit.composeapp.generated.resources.airing_today
import seenit.composeapp.generated.resources.explore
import seenit.composeapp.generated.resources.movies
import seenit.composeapp.generated.resources.now_playing
import seenit.composeapp.generated.resources.on_the_air
import seenit.composeapp.generated.resources.popular
import seenit.composeapp.generated.resources.retry
import seenit.composeapp.generated.resources.top_rated
import seenit.composeapp.generated.resources.tv_shows
import seenit.composeapp.generated.resources.upcoming

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                modifier = Modifier,
                labels = mapOf(
                    "movies" to stringResource(Res.string.movies),
                    "tvShows" to stringResource(Res.string.tv_shows),
                    "nowPlaying" to stringResource(Res.string.now_playing),
                    "popular" to stringResource(Res.string.popular),
                    "topRated" to stringResource(Res.string.top_rated),
                    "upcoming" to stringResource(Res.string.upcoming),
                    "airingToday" to stringResource(Res.string.airing_today),
                    "onTheAir" to stringResource(Res.string.on_the_air),
                    "explore" to stringResource(Res.string.explore),
                    "retry" to stringResource(Res.string.retry)
                )
            )
        }
    }
}