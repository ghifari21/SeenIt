package com.gosty.data.sources.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gosty.data.api.ApiService
import com.gosty.data.api.responses.MovieListsResult

enum class MovieType {
    NOW_PLAYING,
    POPULAR,
    TOP_RATED,
    UPCOMING,
    RECOMMENDATIONS,
    SEARCH,
}

class MoviePagingSource(
    private val apiService: ApiService,
    private val movieType: MovieType,
    private val query: String? = null,
    private val id: Int? = null
) : PagingSource<Int, MovieListsResult>() {

    override fun getRefreshKey(state: PagingState<Int, MovieListsResult>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieListsResult> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val response = when (movieType) {
                MovieType.NOW_PLAYING -> apiService.fetchNowPlayingMovies(position)
                MovieType.POPULAR -> apiService.fetchPopularMovies(position)
                MovieType.TOP_RATED -> apiService.fetchTopRatedMovies(position)
                MovieType.UPCOMING -> apiService.fetchUpcomingMovies(position)
                MovieType.RECOMMENDATIONS -> id?.let {
                    apiService.fetchMovieRecommendations(
                        it,
                        position
                    )
                }

                MovieType.SEARCH -> query?.let { apiService.searchMovies(it, position) }
            }

            LoadResult.Page(
                data = response?.results ?: emptyList(),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (position == response?.totalPages?.toInt()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX = 1
    }
}