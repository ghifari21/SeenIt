package com.gosty.home.models

sealed interface BannerItem {
    val id: Long
    val title: String
    val posterPath: String

    data class Movie(
        override val id: Long,
        override val title: String,
        override val posterPath: String
    ) : BannerItem

    data class TvShow(
        override val id: Long,
        override val title: String,
        override val posterPath: String
    ) : BannerItem
}