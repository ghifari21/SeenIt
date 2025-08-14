package com.gosty.data.api.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TVDetailResponse(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("created_by")
    val createdBy: List<CreatedByResponse>,

    @SerialName("episode_run_time")
    val episodeRunTime: List<Long>,

    @SerialName("first_air_date")
    val firstAirDate: String,

    val genres: List<GenreResponse>,
    val homepage: String,
    val id: Long,

    @SerialName("in_production")
    val inProduction: Boolean,

    val languages: List<String>,

    @SerialName("last_air_date")
    val lastAirDate: String,

    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: TVEpisodeToAirResponse,

    val name: String,

    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: TVEpisodeToAirResponse,

    val networks: List<NetworkResponse>,

    @SerialName("number_of_episodes")
    val numberOfEpisodes: Long,

    @SerialName("number_of_seasons")
    val numberOfSeasons: Long,

    @SerialName("origin_country")
    val originCountry: List<String>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_name")
    val originalName: String,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryResponse>,

    val seasons: List<SeasonResponse>,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>,

    val status: String,
    val tagline: String,
    val type: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)

@Serializable
data class CreatedByResponse(
    val id: Long,

    @SerialName("credit_id")
    val creditID: String,

    val name: String,

    @SerialName("original_name")
    val originalName: String,

    val gender: Long,

    @SerialName("profile_path")
    val profilePath: String
)

@Serializable
data class TVEpisodeToAirResponse(
    val id: Long,
    val name: String,
    val overview: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long,

    @SerialName("air_date")
    val airDate: String,

    @SerialName("episode_number")
    val episodeNumber: Long,

    @SerialName("episode_type")
    val episodeType: String,

    @SerialName("production_code")
    val productionCode: String,

    val runtime: Long,

    @SerialName("season_number")
    val seasonNumber: Long,

    @SerialName("show_id")
    val showID: Long,

    @SerialName("still_path")
    val stillPath: String
)

@Serializable
data class NetworkResponse(
    val id: Long,

    @SerialName("logo_path")
    val logoPath: String,

    val name: String,

    @SerialName("origin_country")
    val originCountry: String
)

@Serializable
data class SeasonResponse(
    @SerialName("air_date")
    val airDate: String,

    @SerialName("episode_count")
    val episodeCount: Long,

    val id: Long,
    val name: String,
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("season_number")
    val seasonNumber: Long,

    @SerialName("vote_average")
    val voteAverage: Double
)