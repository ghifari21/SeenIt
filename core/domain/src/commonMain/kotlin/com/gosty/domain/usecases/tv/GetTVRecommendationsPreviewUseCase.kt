package com.gosty.domain.usecases.tv

import com.gosty.common.models.TV
import com.gosty.data.repositories.TVRepository

class GetTVRecommendationsPreviewUseCase(private val tvRepository: TVRepository) {
    suspend operator fun invoke(tvId: Int): List<TV> =
        tvRepository.getTVRecommendationsPreview(tvId)
}