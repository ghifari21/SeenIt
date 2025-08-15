package com.gosty.domain.models

data class TV(
    val adult: Boolean,
    val backdropPath: String,
    val genreIDS: List<Long>,
    val id: Long,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val firstAirDate: String,
    val name: String,
    val voteAverage: Double,
    val voteCount: Long
)

data class TVDetail(
    val adult: Boolean,
    val backdropPath: String,
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Long>,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val lastEpisodeToAir: TVEpisodeToAir,
    val name: String,
    val nextEpisodeToAir: TVEpisodeToAir,
    val networks: List<Network>,
    val numberOfEpisodes: Long,
    val numberOfSeasons: Long,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Long
)

data class CreatedBy(
    val id: Long,
    val creditId: String,
    val name: String,
    val originalName: String,
    val gender: Long,
    val profilePath: String
)

data class TVEpisodeToAir(
    val id: Long,
    val name: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Long,
    val airDate: String,
    val episodeNumber: Long,
    val episodeType: String,
    val productionCode: String,
    val runtime: Long,
    val seasonNumber: Long,
    val showId: Long,
    val stillPath: String
)

data class Network(
    val id: Long,
    val logoPath: String,
    val name: String,
    val originCountry: String
)

data class Season(
    val airDate: String,
    val episodeCount: Long,
    val id: Long,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Long,
    val voteAverage: Double
)