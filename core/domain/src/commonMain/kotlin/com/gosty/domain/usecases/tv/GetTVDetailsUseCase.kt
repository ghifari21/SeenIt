package com.gosty.domain.usecases.tv

import com.gosty.common.utils.Result
import com.gosty.data.repositories.TVRepository
import com.gosty.domain.models.TVDetail
import kotlinx.coroutines.flow.Flow

class GetTVDetailsUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(tvId: Int): Flow<Result<TVDetail>> = tvRepository.getTVDetails(tvId)
}