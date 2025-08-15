package com.gosty.domain.usecases.tv

import com.gosty.data.repositories.TVRepository
import kotlinx.coroutines.flow.Flow

class IsTVInWatchlistUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(tvId: Int): Flow<Boolean> = tvRepository.isTVInWatchlist(tvId)
}