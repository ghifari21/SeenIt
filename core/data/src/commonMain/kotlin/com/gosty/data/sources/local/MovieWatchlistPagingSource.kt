package com.gosty.data.sources.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gosty.data.db.dao.MovieWatchlistDao
import com.gosty.data.db.entities.MovieWatchlistEntity

class MovieWatchlistPagingSource(
    private val movieWatchlistDao: MovieWatchlistDao
) : PagingSource<Int, MovieWatchlistEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MovieWatchlistEntity>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieWatchlistEntity> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val movies = movieWatchlistDao.getAllMovies(position, position * params.loadSize)

            LoadResult.Page(
                data = movies,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX = 0
    }
}