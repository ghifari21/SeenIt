package com.gosty.data.sources.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gosty.data.api.ApiService
import com.gosty.data.api.responses.TVListsResult

enum class TVType {
    POPULAR,
    TOP_RATED,
    ON_THE_AIR,
    AIRING_TODAY,
    RECOMMENDATIONS,
    SEARCH,
}

class TVPagingSource(
    private val apiService: ApiService,
    private val tvType: TVType,
    private val query: String? = null,
    private val id: Int? = null
) : PagingSource<Int, TVListsResult>() {
    override fun getRefreshKey(state: PagingState<Int, TVListsResult>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVListsResult> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val response = when (tvType) {
                TVType.POPULAR -> apiService.fetchTVPopular(position)
                TVType.TOP_RATED -> apiService.fetchTVTopRated(position)
                TVType.ON_THE_AIR -> apiService.fetchTVOnTheAir(position)
                TVType.AIRING_TODAY -> apiService.fetchTVAiringToday(position)
                TVType.RECOMMENDATIONS -> id?.let {
                    apiService.fetchTVRecommendations(
                        it,
                        position
                    )
                }

                TVType.SEARCH -> query?.let { apiService.searchTVShows(it, position) }
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