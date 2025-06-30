package com.example.domain.models

data class MovieRecommendations(
    val page: Int = 1,
    val results: List<MovieModel> = emptyList(),
)