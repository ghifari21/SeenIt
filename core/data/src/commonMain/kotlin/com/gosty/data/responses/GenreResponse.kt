package com.gosty.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val id: Long,
    val name: String
)