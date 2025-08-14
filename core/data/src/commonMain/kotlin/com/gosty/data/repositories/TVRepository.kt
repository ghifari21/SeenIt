package com.gosty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gosty.common.models.TV
import com.gosty.common.models.TVDetail
import com.gosty.common.models.TVWatchlist
import com.gosty.common.utils.Result
import com.gosty.data.sources.local.LocalDataSource
import com.gosty.data.sources.remote.RemoteDataSource
import com.gosty.data.utils.toEntity
import com.gosty.data.utils.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface TVRepository {
    fun getAiringTodayTVShows(): Flow<PagingData<TV>>
    fun getOnTheAirTVShows(): Flow<PagingData<TV>>
    fun getPopularTVShows(): Flow<PagingData<TV>>
    fun getTopRatedTVShows(): Flow<PagingData<TV>>
    fun getTVDetails(tvId: Int): Flow<Result<TVDetail>>
    fun getTVRecommendations(tvId: Int): Flow<PagingData<TV>>
    fun searchTVShows(query: String): Flow<PagingData<TV>>
    fun addTVToWatchlist(tv: TV): Flow<String>
    fun addTVToWatchlist(tv: TVDetail): Flow<String>
    fun addTVToWatchlist(tv: TVWatchlist): Flow<String>
    fun removeTVFromWatchlist(tv: TV): Flow<String>
    fun removeTVFromWatchlist(tv: TVDetail): Flow<String>
    fun removeTVFromWatchlist(tv: TVWatchlist): Flow<String>
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
        }
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
        }
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
        }
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
        }
    }

    override fun getTVDetails(tvId: Int): Flow<Result<TVDetail>> = flow {
        emit(Result.Loading)
        try {
            val tv = remoteDataSource.fetchTVDetails(tvId).toModel()
            emit(Result.Success(tv))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
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
        }
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
        }
    }

    override fun addTVToWatchlist(tv: TV): Flow<String> = flow {
        try {
            val result = localDataSource.insertTV(tv.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error adding TV to watchlist: ${e.message}")
        }
    }

    override fun addTVToWatchlist(tv: TVDetail): Flow<String> = flow {
        try {
            val result = localDataSource.insertTV(tv.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error adding TV to watchlist: ${e.message}")
        }
    }

    override fun addTVToWatchlist(tv: TVWatchlist): Flow<String> = flow {
        try {
            val result = localDataSource.insertTV(tv.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error adding TV to watchlist: ${e.message}")
        }
    }

    override fun removeTVFromWatchlist(tv: TV): Flow<String> = flow {
        try {
            val result = localDataSource.deleteTV(tv.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error removing TV from watchlist: ${e.message}")
        }
    }

    override fun removeTVFromWatchlist(tv: TVDetail): Flow<String> = flow {
        try {
            val result = localDataSource.deleteTV(tv.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error removing TV from watchlist: ${e.message}")
        }
    }

    override fun removeTVFromWatchlist(tv: TVWatchlist): Flow<String> = flow {
        try {
            val result = localDataSource.deleteTV(tv.toEntity())
            emit(result)
        } catch (e: Exception) {
            emit("Error removing TV from watchlist: ${e.message}")
        }
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
        }
    }

    override fun isTVInWatchlist(tvId: Int): Flow<Boolean> = flow {
        try {
            val result = localDataSource.isTVInWatchlist(tvId)
            emit(result)
        } catch (e: Exception) {
            emit(false)
        }
    }
}