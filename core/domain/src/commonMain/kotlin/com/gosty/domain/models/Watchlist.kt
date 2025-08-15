package com.gosty.domain.models

data class TVWatchlist(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
)

data class MovieWatchlist(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
)