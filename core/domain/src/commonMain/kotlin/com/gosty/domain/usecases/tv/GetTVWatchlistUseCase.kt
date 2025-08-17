package com.gosty.domain.usecases.tv

import androidx.paging.PagingData
import com.gosty.common.models.TVWatchlist
import com.gosty.data.repositories.TVRepository
import kotlinx.coroutines.flow.Flow

class GetTVWatchlistUseCase(private val tvRepository: TVRepository) {
    operator fun invoke(): Flow<PagingData<TVWatchlist>> = tvRepository.getTVWatchlist()
}