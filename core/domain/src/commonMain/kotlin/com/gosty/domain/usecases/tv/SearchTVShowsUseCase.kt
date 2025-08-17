package com.gosty.domain.usecases.tv

import androidx.paging.PagingData
import com.gosty.common.models.TV
import com.gosty.data.repositories.TVRepository
import kotlinx.coroutines.flow.Flow

class SearchTVShowsUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(query: String): Flow<PagingData<TV>> = tvRepository.searchTVShows(query)
}