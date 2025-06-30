package com.example.domain.models

data class MoviesPageModel(
    val page: Int = 1,
    val results: List<MovieModel> = emptyList(),
)