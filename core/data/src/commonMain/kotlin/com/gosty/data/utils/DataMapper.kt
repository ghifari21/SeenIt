package com.gosty.data.utils

import com.gosty.common.models.BelongsToCollection
import com.gosty.common.models.CreatedBy
import com.gosty.common.models.Genre
import com.gosty.common.models.Movie
import com.gosty.common.models.MovieDetail
import com.gosty.common.models.MovieWatchlist
import com.gosty.common.models.Network
import com.gosty.common.models.ProductionCompany
import com.gosty.common.models.ProductionCountry
import com.gosty.common.models.Season
import com.gosty.common.models.SpokenLanguage
import com.gosty.common.models.TV
import com.gosty.common.models.TVDetail
import com.gosty.common.models.TVEpisodeToAir
import com.gosty.common.models.TVWatchlist
import com.gosty.data.api.responses.BelongsToCollectionResponse
import com.gosty.data.api.responses.CreatedByResponse
import com.gosty.data.api.responses.GenreResponse
import com.gosty.data.api.responses.MovieDetailResponse
import com.gosty.data.api.responses.MovieListsResult
import com.gosty.data.api.responses.NetworkResponse
import com.gosty.data.api.responses.ProductionCompanyResponse
import com.gosty.data.api.responses.ProductionCountryResponse
import com.gosty.data.api.responses.SeasonResponse
import com.gosty.data.api.responses.SpokenLanguageResponse
import com.gosty.data.api.responses.TVDetailResponse
import com.gosty.data.api.responses.TVEpisodeToAirResponse
import com.gosty.data.api.responses.TVListsResult
import com.gosty.data.db.entities.MovieWatchlistEntity
import com.gosty.data.db.entities.TVWatchlistEntity

fun MovieListsResult.toModel(): Movie {
    return Movie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIDS = this.genreIDS,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun Movie.toResponse(): MovieListsResult {
    return MovieListsResult(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIDS = this.genreIDS,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun Movie.toEntity(): MovieWatchlistEntity {
    return MovieWatchlistEntity(
        id = this.id.toInt(),
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath
    )
}

fun MovieWatchlistEntity.toModel(): MovieWatchlist {
    return MovieWatchlist(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath
    )
}

fun MovieWatchlist.toEntity(): MovieWatchlistEntity {
    return MovieWatchlistEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath
    )
}

fun MovieDetailResponse.toModel(): MovieDetail {
    return MovieDetail(
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsToCollection = BelongsToCollection(
            id = this.belongsToCollection.id,
            name = this.belongsToCollection.name,
            posterPath = this.belongsToCollection.posterPath,
            backdropPath = this.belongsToCollection.backdropPath
        ),
        budget = this.budget,
        genres = this.genres.map { genre ->
            Genre(
                id = genre.id,
                name = genre.name
            )
        },
        homepage = this.homepage,
        id = this.id,
        imdbID = this.imdbID,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { company ->
            ProductionCompany(
                id = company.id,
                logoPath = company.logoPath,
                name = company.name,
                originCountry = company.originCountry
            )
        },
        productionCountries = this.productionCountries.map { country ->
            ProductionCountry(
                iso31661 = country.iso31661,
                name = country.name
            )
        },
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = this.spokenLanguages.map { language ->
            SpokenLanguage(
                iso6391 = language.iso6391,
                name = language.name,
                englishName = language.englishName
            )
        },
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun MovieDetail.toResponse(): MovieDetailResponse {
    return MovieDetailResponse(
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsToCollection = BelongsToCollectionResponse(
            id = this.belongsToCollection.id,
            name = this.belongsToCollection.name,
            posterPath = this.belongsToCollection.posterPath,
            backdropPath = this.belongsToCollection.backdropPath
        ),
        budget = this.budget,
        genres = this.genres.map { genre ->
            GenreResponse(
                id = genre.id,
                name = genre.name
            )
        },
        homepage = this.homepage,
        id = this.id,
        imdbID = this.imdbID,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { company ->
            ProductionCompanyResponse(
                id = company.id,
                logoPath = company.logoPath,
                name = company.name,
                originCountry = company.originCountry
            )
        },
        productionCountries = this.productionCountries.map { country ->
            ProductionCountryResponse(
                iso31661 = country.iso31661,
                name = country.name
            )
        },
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = this.spokenLanguages.map { language ->
            SpokenLanguageResponse(
                iso6391 = language.iso6391,
                name = language.name,
                englishName = language.englishName
            )
        },
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun MovieDetail.toEntity(): MovieWatchlistEntity {
    return MovieWatchlistEntity(
        id = this.id.toInt(),
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath
    )
}

fun TVListsResult.toModel(): TV {
    return TV(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIDS = this.genreIDS,
        id = this.id,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        firstAirDate = this.firstAirDate,
        name = this.name,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun TV.toResponse(): TVListsResult {
    return TVListsResult(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIDS = this.genreIDS,
        id = this.id,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        firstAirDate = this.firstAirDate,
        name = this.name,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun TV.toEntity(): TVWatchlistEntity {
    return TVWatchlistEntity(
        id = this.id.toInt(),
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath
    )
}

fun TVDetailResponse.toModel(): TVDetail {
    return TVDetail(
        adult = this.adult,
        backdropPath = this.backdropPath,
        createdBy = this.createdBy.map { creator ->
            CreatedBy(
                id = creator.id,
                creditId = creator.creditID,
                name = creator.name,
                originalName = creator.originalName,
                gender = creator.gender,
                profilePath = creator.profilePath
            )
        },
        episodeRunTime = this.episodeRunTime,
        firstAirDate = this.firstAirDate,
        genres = this.genres.map { genre ->
            Genre(
                id = genre.id,
                name = genre.name
            )
        },
        homepage = this.homepage,
        id = this.id,
        inProduction = this.inProduction,
        languages = this.languages,
        lastAirDate = this.lastAirDate,
        lastEpisodeToAir = TVEpisodeToAir(
            id = this.lastEpisodeToAir.id,
            name = this.lastEpisodeToAir.name,
            overview = this.lastEpisodeToAir.overview,
            voteAverage = this.lastEpisodeToAir.voteAverage,
            voteCount = this.lastEpisodeToAir.voteCount,
            airDate = this.lastEpisodeToAir.airDate,
            episodeNumber = this.lastEpisodeToAir.episodeNumber,
            episodeType = this.lastEpisodeToAir.episodeType,
            productionCode = this.lastEpisodeToAir.productionCode,
            runtime = this.lastEpisodeToAir.runtime,
            seasonNumber = this.lastEpisodeToAir.seasonNumber,
            showId = this.lastEpisodeToAir.showID,
            stillPath = this.lastEpisodeToAir.stillPath
        ),
        name = this.name,
        nextEpisodeToAir = TVEpisodeToAir(
            id = this.nextEpisodeToAir.id,
            name = this.nextEpisodeToAir.name,
            overview = this.nextEpisodeToAir.overview,
            voteAverage = this.nextEpisodeToAir.voteAverage,
            voteCount = this.nextEpisodeToAir.voteCount,
            airDate = this.nextEpisodeToAir.airDate,
            episodeNumber = this.nextEpisodeToAir.episodeNumber,
            episodeType = this.nextEpisodeToAir.episodeType,
            productionCode = this.nextEpisodeToAir.productionCode,
            runtime = this.nextEpisodeToAir.runtime,
            seasonNumber = this.nextEpisodeToAir.seasonNumber,
            showId = this.nextEpisodeToAir.showID,
            stillPath = this.nextEpisodeToAir.stillPath
        ),
        networks = this.networks.map { network ->
            Network(
                id = network.id,
                logoPath = network.logoPath,
                name = network.name,
                originCountry = network.originCountry
            )
        },
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { company ->
            ProductionCompany(
                id = company.id,
                logoPath = company.logoPath,
                name = company.name,
                originCountry = company.originCountry
            )
        },
        productionCountries = this.productionCountries.map { country ->
            ProductionCountry(
                iso31661 = country.iso31661,
                name = country.name
            )
        },
        seasons = this.seasons.map { season ->
            Season(
                airDate = season.airDate,
                episodeCount = season.episodeCount,
                id = season.id,
                name = season.name,
                overview = season.overview,
                posterPath = season.posterPath,
                seasonNumber = season.seasonNumber,
                voteAverage = season.voteAverage,
            )
        },
        spokenLanguages = this.spokenLanguages.map { language ->
            SpokenLanguage(
                iso6391 = language.iso6391,
                name = language.name,
                englishName = language.englishName
            )
        },
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun TVDetail.toResponse(): TVDetailResponse {
    return TVDetailResponse(
        adult = this.adult,
        backdropPath = this.backdropPath,
        createdBy = this.createdBy.map { creator ->
            CreatedByResponse(
                id = creator.id,
                creditID = creator.creditId,
                name = creator.name,
                originalName = creator.originalName,
                gender = creator.gender,
                profilePath = creator.profilePath
            )
        },
        episodeRunTime = this.episodeRunTime,
        firstAirDate = this.firstAirDate,
        genres = this.genres.map { genre ->
            GenreResponse(
                id = genre.id,
                name = genre.name
            )
        },
        homepage = this.homepage,
        id = this.id,
        inProduction = this.inProduction,
        languages = this.languages,
        lastAirDate = this.lastAirDate,
        lastEpisodeToAir = TVEpisodeToAirResponse(
            id = this.lastEpisodeToAir.id,
            name = this.lastEpisodeToAir.name,
            overview = this.lastEpisodeToAir.overview,
            voteAverage = this.lastEpisodeToAir.voteAverage,
            voteCount = this.lastEpisodeToAir.voteCount,
            airDate = this.lastEpisodeToAir.airDate,
            episodeNumber = this.lastEpisodeToAir.episodeNumber,
            episodeType = this.lastEpisodeToAir.episodeType,
            productionCode = this.lastEpisodeToAir.productionCode,
            runtime = this.lastEpisodeToAir.runtime,
            seasonNumber = this.lastEpisodeToAir.seasonNumber,
            showID = this.lastEpisodeToAir.showId,
            stillPath = this.lastEpisodeToAir.stillPath
        ),
        name = this.name,
        nextEpisodeToAir = TVEpisodeToAirResponse(
            id = this.nextEpisodeToAir.id,
            name = this.nextEpisodeToAir.name,
            overview = this.nextEpisodeToAir.overview,
            voteAverage = this.nextEpisodeToAir.voteAverage,
            voteCount = this.nextEpisodeToAir.voteCount,
            airDate = this.nextEpisodeToAir.airDate,
            episodeNumber = this.nextEpisodeToAir.episodeNumber,
            episodeType = this.nextEpisodeToAir.episodeType,
            productionCode = this.nextEpisodeToAir.productionCode,
            runtime = this.nextEpisodeToAir.runtime,
            seasonNumber = this.nextEpisodeToAir.seasonNumber,
            showID = this.nextEpisodeToAir.showId,
            stillPath = this.nextEpisodeToAir.stillPath
        ),
        networks = this.networks.map { network ->
            NetworkResponse(
                id = network.id,
                logoPath = network.logoPath,
                name = network.name,
                originCountry = network.originCountry
            )
        },
        numberOfEpisodes = this.numberOfEpisodes,
        numberOfSeasons = this.numberOfSeasons,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = this.productionCompanies.map { company ->
            ProductionCompanyResponse(
                id = company.id,
                logoPath = company.logoPath,
                name = company.name,
                originCountry = company.originCountry
            )
        },
        productionCountries = this.productionCountries.map { country ->
            ProductionCountryResponse(
                iso31661 = country.iso31661,
                name = country.name
            )
        },
        seasons = this.seasons.map { season ->
            SeasonResponse(
                airDate = season.airDate,
                episodeCount = season.episodeCount,
                id = season.id,
                name = season.name,
                overview = season.overview,
                posterPath = season.posterPath,
                seasonNumber = season.seasonNumber,
                voteAverage = season.voteAverage,
            )
        },
        spokenLanguages = this.spokenLanguages.map { language ->
            SpokenLanguageResponse(
                iso6391 = language.iso6391,
                name = language.name,
                englishName = language.englishName
            )
        },
        status = this.status,
        tagline = this.tagline,
        type = this.type,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun TVDetail.toEntity(): TVWatchlistEntity {
    return TVWatchlistEntity(
        id = this.id.toInt(),
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath
    )
}

fun TVWatchlistEntity.toModel(): TVWatchlist {
    return TVWatchlist(
        id = this.id,
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath
    )
}

fun TVWatchlist.toEntity(): TVWatchlistEntity {
    return TVWatchlistEntity(
        id = this.id,
        name = this.name,
        overview = this.overview,
        posterPath = this.posterPath
    )
}