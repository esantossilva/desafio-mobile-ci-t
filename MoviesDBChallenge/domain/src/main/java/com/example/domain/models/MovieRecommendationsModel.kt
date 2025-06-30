package com.example.domain.models

data class MovieRecommendationsModel(
    val page: Int = 1,
    val results: List<MovieModel> = emptyList(),
)