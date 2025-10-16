package com.gosty.explore.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gosty.common.models.Movie
import com.gosty.common.models.TV
import com.gosty.common.routes.ExploreCategory
import com.gosty.domain.usecases.movie.GetNowPlayingMoviesUseCase
import com.gosty.domain.usecases.movie.GetPopularMoviesUseCase
import com.gosty.domain.usecases.movie.GetTopRatedMoviesUseCase
import com.gosty.domain.usecases.movie.GetUpcomingMoviesUseCase
import com.gosty.domain.usecases.tv.GetAiringTodayTVShowsUseCase
import com.gosty.domain.usecases.tv.GetOnTheAirTVShowsUseCase
import com.gosty.domain.usecases.tv.GetPopularTVShowsUseCase
import com.gosty.domain.usecases.tv.GetTopRatedTVShowsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ExploreState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val title: String = "Explore",
    val category: ExploreCategory? = null,
    val tvShows: Flow<PagingData<TV>>? = null,
    val movies: Flow<PagingData<Movie>>? = null,
)

sealed class ExploreEvent {
    data class Load(val category: ExploreCategory) : ExploreEvent()
    data class Refresh(val category: ExploreCategory) : ExploreEvent()
}

class ExploreViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getAiringTodayTVShowsUseCase: GetAiringTodayTVShowsUseCase,
    private val getOnTheAirTVShowsUseCase: GetOnTheAirTVShowsUseCase,
    private val getPopularTVShowsUseCase: GetPopularTVShowsUseCase,
    private val getTopRatedTVShowsUseCase: GetTopRatedTVShowsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreState())
    val uiState: StateFlow<ExploreState> get() = _uiState.asStateFlow()

    init {
        val categoryName: String = savedStateHandle["category"]!!
        val category = ExploreCategory.valueOf(value = categoryName)

        val title = category.name.replace("_", " ").lowercase().replaceFirstChar { it.titlecase() }
        _uiState.update { it.copy(title = title, category = category) }

        onEvent(event = ExploreEvent.Load(category = category))
    }

    fun onEvent(event: ExploreEvent) {
        when (event) {
            is ExploreEvent.Load -> loadContent(category = event.category)
            is ExploreEvent.Refresh -> onEvent(event = ExploreEvent.Load(category = event.category))
        }
    }

    private fun loadContent(category: ExploreCategory) {
        _uiState.update { it.copy(isLoading = true, isError = false, errorMessage = "") }

        val tvResult = when (category) {
            ExploreCategory.AIRING_TODAY_TV -> getAiringTodayTVShowsUseCase()
            ExploreCategory.ON_THE_AIR_TV -> getOnTheAirTVShowsUseCase()
            ExploreCategory.POPULAR_TV -> getPopularTVShowsUseCase()
            ExploreCategory.TOP_RATED_TV -> getTopRatedTVShowsUseCase()
            else -> null
        }?.cachedIn(viewModelScope)

        val movieResult = when (category) {
            ExploreCategory.NOW_PLAYING_MOVIES -> getNowPlayingMoviesUseCase()
            ExploreCategory.POPULAR_MOVIES -> getPopularMoviesUseCase()
            ExploreCategory.TOP_RATED_MOVIES -> getTopRatedMoviesUseCase()
            ExploreCategory.UPCOMING_MOVIES -> getUpcomingMoviesUseCase()
            else -> null
        }?.cachedIn(viewModelScope)

        _uiState.update {
            it.copy(
                isLoading = false,
                isError = false,
                tvShows = tvResult,
                movies = movieResult
            )
        }
    }
}