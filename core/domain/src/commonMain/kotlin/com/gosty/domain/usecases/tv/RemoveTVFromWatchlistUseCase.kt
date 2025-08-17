package com.gosty.domain.usecases.tv

import com.gosty.common.models.TV
import com.gosty.common.models.TVDetail
import com.gosty.common.models.TVWatchlist
import com.gosty.data.repositories.TVRepository

class RemoveTVFromWatchlistUseCase(private val tvRepository: TVRepository) {
    suspend operator fun invoke(tv: TV) = tvRepository.removeTVFromWatchlist(tv)
    suspend operator fun invoke(tv: TVDetail) = tvRepository.removeTVFromWatchlist(tv)
    suspend operator fun invoke(tv: TVWatchlist) = tvRepository.removeTVFromWatchlist(tv)
}