package com.gosty.domain.usecases.tv

import com.gosty.common.utils.Result
import com.gosty.data.repositories.TVRepository
import com.gosty.domain.models.TV
import kotlinx.coroutines.flow.Flow

class GetTVRecommendationsPreviewUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(tvId: Int): Flow<Result<List<TV>>> =
        tvRepository.getTVRecommendationsPreview(tvId)
}