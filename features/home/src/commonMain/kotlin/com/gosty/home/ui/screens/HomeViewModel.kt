package com.gosty.home.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gosty.common.models.Movie
import com.gosty.common.models.TV
import com.gosty.domain.usecases.movie.GetNowPlayingMoviesPreviewUseCase
import com.gosty.domain.usecases.movie.GetPopularMoviesPreviewUseCase
import com.gosty.domain.usecases.movie.GetTopRatedMoviesPreviewUseCase
import com.gosty.domain.usecases.movie.GetUpcomingMoviesPreviewUseCase
import com.gosty.domain.usecases.tv.GetAiringTodayTVShowsPreviewUseCase
import com.gosty.domain.usecases.tv.GetOnTheAirTVShowsPreviewUseCase
import com.gosty.domain.usecases.tv.GetPopularTVShowsPreviewUseCase
import com.gosty.domain.usecases.tv.GetTopRatedTVShowsPreviewUseCase
import com.gosty.home.models.BannerItem
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeState(
    val isLoadingBanner: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val bannerItems: List<BannerItem> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val topRatedMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val onTheAirTVShows: List<TV> = emptyList(),
    val airingTodayTVShows: List<TV> = emptyList(),
    val popularTVShows: List<TV> = emptyList(),
    val topRatedTVShows: List<TV> = emptyList()
)

sealed class HomeEvent {
    data object LoadBanner : HomeEvent()
    data object LoadAll : HomeEvent()
    data object Refresh : HomeEvent()
}

class HomeViewModel(
    private val getPopularMoviesPreviewUseCase: GetPopularMoviesPreviewUseCase,
    private val getNowPlayingMoviesPreviewUseCase: GetNowPlayingMoviesPreviewUseCase,
    private val getTopRatedMoviesPreviewUseCase: GetTopRatedMoviesPreviewUseCase,
    private val getUpcomingMoviesPreviewUseCase: GetUpcomingMoviesPreviewUseCase,
    private val getOnTheAirTVShowsPreviewUseCase: GetOnTheAirTVShowsPreviewUseCase,
    private val getAiringTodayTVShowsPreviewUseCase: GetAiringTodayTVShowsPreviewUseCase,
    private val getPopularTVShowsPreviewUseCase: GetPopularTVShowsPreviewUseCase,
    private val getTopRatedTVShowsPreviewUseCase: GetTopRatedTVShowsPreviewUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> get() = _uiState.asStateFlow()

    init {
        onEvent(HomeEvent.LoadBanner)
        onEvent(HomeEvent.LoadAll)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadBanner -> loadBanner()
            is HomeEvent.LoadAll -> loadAllData()
            is HomeEvent.Refresh -> {
                loadBanner()
                loadAllData()
            }
        }
    }

    private fun loadBanner() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingBanner = true, isError = false, errorMessage = "") }
            try {
                val moviesDeferred = async { getNowPlayingMoviesPreviewUseCase() }
                val tvShowsDeferred = async { getOnTheAirTVShowsPreviewUseCase() }

                val movies = moviesDeferred.await().take(n = 4)
                val tvShows = tvShowsDeferred.await().take(n = 4)

                val movieItems = movies.map {
                    BannerItem.Movie(
                        id = it.id,
                        title = it.title,
                        posterPath = it.posterPath
                    )
                }
                val tvShowItems = tvShows.map {
                    BannerItem.TvShow(
                        id = it.id,
                        title = it.name,
                        posterPath = it.posterPath
                    )
                }

                val combinedList = (movieItems + tvShowItems).shuffled()

                _uiState.update {
                    it.copy(
                        isLoadingBanner = false,
                        bannerItems = combinedList,
                        isError = false,
                        errorMessage = ""
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoadingBanner = false,
                        isError = true,
                        errorMessage = e.message.toString()
                    )
                }
            }
        }
    }

    private fun loadAllData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, isError = false, errorMessage = "") }
            try {
                val nowPlayingMoviesDeferred = async { getNowPlayingMoviesPreviewUseCase() }
                val popularMoviesDeferred = async { getPopularMoviesPreviewUseCase() }
                val topRatedMoviesDeferred = async { getTopRatedMoviesPreviewUseCase() }
                val upcomingMoviesDeferred = async { getUpcomingMoviesPreviewUseCase() }
                val onTheAirTVShowsDeferred = async { getOnTheAirTVShowsPreviewUseCase() }
                val airingTodayTVShowsDeferred = async { getAiringTodayTVShowsPreviewUseCase() }
                val popularTVShowsDeferred = async { getPopularTVShowsPreviewUseCase() }
                val topRatedTVShowsDeferred = async { getTopRatedTVShowsPreviewUseCase() }

                val nowPlayingMovies = nowPlayingMoviesDeferred.await()
                val popularMovies = popularMoviesDeferred.await()
                val topRatedMovies = topRatedMoviesDeferred.await()
                val upcomingMovies = upcomingMoviesDeferred.await()
                val onTheAirTVShows = onTheAirTVShowsDeferred.await()
                val airingTodayTVShows = airingTodayTVShowsDeferred.await()
                val popularTVShows = popularTVShowsDeferred.await()
                val topRatedTVShows = topRatedTVShowsDeferred.await()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        nowPlayingMovies = nowPlayingMovies,
                        popularMovies = popularMovies,
                        topRatedMovies = topRatedMovies,
                        upcomingMovies = upcomingMovies,
                        onTheAirTVShows = onTheAirTVShows,
                        airingTodayTVShows = airingTodayTVShows,
                        popularTVShows = popularTVShows,
                        topRatedTVShows = topRatedTVShows,
                        isError = false,
                        errorMessage = ""
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = e.message.toString()
                    )
                }
            }
        }
    }
}