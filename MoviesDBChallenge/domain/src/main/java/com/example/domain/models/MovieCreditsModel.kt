package com.example.domain.models

data class MovieCreditsModel(
    val id: Int = 0,
    val cast: List<CastModel> = emptyList(),
)
