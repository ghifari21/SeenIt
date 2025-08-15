package com.gosty.domain.usecases.tv

import com.gosty.common.utils.Result
import com.gosty.data.repositories.TVRepository
import com.gosty.domain.models.TV
import kotlinx.coroutines.flow.Flow

class GetOnTheAirTVShowsPreviewUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(): Flow<Result<List<TV>>> = tvRepository.getOnTheAirTVShowsPreview()
}