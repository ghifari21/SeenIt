package com.gosty.domain.usecases.tv

import com.gosty.data.repositories.TVRepository
import com.gosty.domain.models.TV
import com.gosty.domain.models.TVDetail
import com.gosty.domain.models.TVWatchlist
import kotlinx.coroutines.flow.Flow

class RemoveTVFromWatchlistUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(tv: TV): Flow<String> = tvRepository.removeTVFromWatchlist(tv)
    operator fun invoke(tv: TVDetail): Flow<String> = tvRepository.removeTVFromWatchlist(tv)
    operator fun invoke(tv: TVWatchlist): Flow<String> = tvRepository.removeTVFromWatchlist(tv)
}