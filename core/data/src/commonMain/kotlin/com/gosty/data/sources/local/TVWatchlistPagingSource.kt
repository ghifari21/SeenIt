package com.gosty.data.sources.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gosty.data.db.dao.TVWatchlistDao
import com.gosty.data.db.entities.TVWatchlistEntity

class TVWatchlistPagingSource(
    private val tvWatchlistDao: TVWatchlistDao
) : PagingSource<Int, TVWatchlistEntity>() {
    override fun getRefreshKey(state: PagingState<Int, TVWatchlistEntity>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVWatchlistEntity> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val tvShows = tvWatchlistDao.getAllTVs(position, position * params.loadSize)

            LoadResult.Page(
                data = tvShows,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (tvShows.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX = 0
    }
}