package com.gosty.domain.usecases.tv

import com.gosty.common.models.TV
import com.gosty.data.repositories.TVRepository

class GetAiringTodayTVShowsPreviewUseCase(private val tvRepository: TVRepository) {
    suspend operator fun invoke(): List<TV> = tvRepository.getAiringTodayTVShowsPreview()
}