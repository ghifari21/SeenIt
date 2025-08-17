package com.gosty.domain.usecases.tv

import com.gosty.common.models.TVDetail
import com.gosty.data.repositories.TVRepository

class GetTVDetailsUseCase(private val tvRepository: TVRepository) {
    suspend operator fun invoke(tvId: Int): TVDetail = tvRepository.getTVDetails(tvId)
}