package com.gosty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gosty.common.models.TV
import com.gosty.common.models.TVDetail
import com.gosty.common.models.TVWatchlist
import com.gosty.data.sources.local.LocalDataSource
import com.gosty.data.sources.remote.RemoteDataSource
import com.gosty.data.utils.toEntity
import com.gosty.data.utils.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface TVRepository {
    fun getAiringTodayTVShows(): Flow<PagingData<TV>>
    suspend fun getAiringTodayTVShowsPreview(): List<TV>
    fun getOnTheAirTVShows(): Flow<PagingData<TV>>
    suspend fun getOnTheAirTVShowsPreview(): List<TV>
    fun getPopularTVShows(): Flow<PagingData<TV>>
    suspend fun getPopularTVShowsPreview(): List<TV>
    fun getTopRatedTVShows(): Flow<PagingData<TV>>
    suspend fun getTopRatedTVShowsPreview(): List<TV>
    suspend fun getTVDetails(tvId: Int): TVDetail
    fun getTVRecommendations(tvId: Int): Flow<PagingData<TV>>
    suspend fun getTVRecommendationsPreview(tvId: Int): List<TV>
    fun searchTVShows(query: String): Flow<PagingData<TV>>
    suspend fun addTVToWatchlist(tv: TV)
    suspend fun addTVToWatchlist(tv: TVDetail)
    suspend fun addTVToWatchlist(tv: TVWatchlist)
    suspend fun removeTVFromWatchlist(tv: TV)
    suspend fun removeTVFromWatchlist(tv: TVDetail)
    suspend fun removeTVFromWatchlist(tv: TVWatchlist)
    fun getTVWatchlist(): Flow<PagingData<TVWatchlist>>
    fun isTVInWatchlist(tvId: Int): Flow<Boolean>
}

class TVRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : TVRepository {
    override fun getAiringTodayTVShows(): Flow<PagingData<TV>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchTVAiringToday()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAiringTodayTVShowsPreview(): List<TV> {
        return remoteDataSource.fetchTVAiringTodayPreview().results.map { it.toModel() }
    }

    override fun getOnTheAirTVShows(): Flow<PagingData<TV>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchTVOnTheAir()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getOnTheAirTVShowsPreview(): List<TV> {
        return remoteDataSource.fetchTVOnTheAirPreview().results.map { it.toModel() }
    }

    override fun getPopularTVShows(): Flow<PagingData<TV>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchTVPopular()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPopularTVShowsPreview(): List<TV> {
        return remoteDataSource.fetchTVPopularPreview().results.map { it.toModel() }
    }

    override fun getTopRatedTVShows(): Flow<PagingData<TV>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchTVTopRated()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTopRatedTVShowsPreview(): List<TV> {
        return remoteDataSource.fetchTVTopRatedPreview().results.map { it.toModel() }
    }

    override suspend fun getTVDetails(tvId: Int): TVDetail {
        return remoteDataSource.fetchTVDetails(tvId).toModel()
    }

    override fun getTVRecommendations(tvId: Int): Flow<PagingData<TV>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.fetchTVRecommendations(tvId)
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTVRecommendationsPreview(tvId: Int): List<TV> {
        return remoteDataSource.fetchTVRecommendationsPreview(tvId).results.map { it.toModel() }
    }

    override fun searchTVShows(query: String): Flow<PagingData<TV>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                remoteDataSource.searchTVShows(query)
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addTVToWatchlist(tv: TV) {
        return localDataSource.insertTV(tv.toEntity())
    }

    override suspend fun addTVToWatchlist(tv: TVDetail) {
        return localDataSource.insertTV(tv.toEntity())
    }

    override suspend fun addTVToWatchlist(tv: TVWatchlist) {
        return localDataSource.insertTV(tv.toEntity())
    }

    override suspend fun removeTVFromWatchlist(tv: TV) {
        return localDataSource.deleteTV(tv.toEntity())
    }

    override suspend fun removeTVFromWatchlist(tv: TVDetail) {
        return localDataSource.deleteTV(tv.toEntity())
    }

    override suspend fun removeTVFromWatchlist(tv: TVWatchlist) {
        return localDataSource.deleteTV(tv.toEntity())
    }

    override fun getTVWatchlist(): Flow<PagingData<TVWatchlist>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                localDataSource.getAllTVShows()
            }
        ).flow.map { responses ->
            responses.map { it.toModel() }
        }.flowOn(Dispatchers.IO)
    }

    override fun isTVInWatchlist(tvId: Int): Flow<Boolean> = flow {
        try {
            val result = localDataSource.isTVInWatchlist(tvId)
            emit(result)
        } catch (e: Exception) {
            emit(false)
        }
    }.flowOn(Dispatchers.IO)
}