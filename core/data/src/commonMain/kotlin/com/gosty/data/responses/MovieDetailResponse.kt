package com.gosty.data.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionResponse,

    val budget: Long,
    val genres: List<GenreResponse>,
    val homepage: String,
    val id: Long,

    @SerialName("imdb_id")
    val imdbID: String,

    @SerialName("origin_country")
    val originCountry: List<String>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse>,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryResponse>,

    @SerialName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)

@Serializable
data class BelongsToCollectionResponse(
    val id: Long,
    val name: String,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("backdrop_path")
    val backdropPath: String
)

@Serializable
data class ProductionCompanyResponse(
    val id: Long,

    @SerialName("logo_path")
    val logoPath: String,

    val name: String,

    @SerialName("origin_country")
    val originCountry: String
)

