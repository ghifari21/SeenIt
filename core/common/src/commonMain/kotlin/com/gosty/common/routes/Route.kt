package com.gosty.common.routes

val destinations = listOf(
    Screen.Home.route,
    Screen.Search.route,
    Screen.Watchlist.route
)

val navItem = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Watchlist
)

enum class ExploreCategory {
    NOW_PLAYING_MOVIES,
    POPULAR_MOVIES,
    TOP_RATED_MOVIES,
    UPCOMING_MOVIES,
    AIRING_TODAY_TV,
    ON_THE_AIR_TV,
    POPULAR_TV,
    TOP_RATED_TV
}

enum class ContentType {
    MOVIE,
    TV_SHOW
}