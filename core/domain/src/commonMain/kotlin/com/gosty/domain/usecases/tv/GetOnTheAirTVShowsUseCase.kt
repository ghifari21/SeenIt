package com.gosty.domain.usecases.tv

import androidx.paging.PagingData
import com.gosty.data.repositories.TVRepository
import com.gosty.domain.models.TV
import kotlinx.coroutines.flow.Flow

class GetOnTheAirTVShowsUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(): Flow<PagingData<TV>> = tvRepository.getOnTheAirTVShows()
}