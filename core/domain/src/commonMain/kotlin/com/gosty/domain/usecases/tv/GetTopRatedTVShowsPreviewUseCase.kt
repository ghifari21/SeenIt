package com.gosty.domain.usecases.tv

import com.gosty.common.models.TV
import com.gosty.data.repositories.TVRepository

class GetTopRatedTVShowsPreviewUseCase(private val tvRepository: TVRepository) {
    suspend operator fun invoke(): List<TV> = tvRepository.getTopRatedTVShowsPreview()
}