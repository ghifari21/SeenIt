package com.gosty.domain.usecases.tv

import androidx.paging.PagingData
import com.gosty.common.models.TV
import com.gosty.data.repositories.TVRepository
import kotlinx.coroutines.flow.Flow

class GetTVRecommendationsUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(tvId: Int): Flow<PagingData<TV>> = tvRepository.getTVRecommendations(tvId)
}