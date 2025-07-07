package com.example.domain.models

import com.example.domain.enums.MediaType

data class MovieModel(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val genreIds: List<Int> = emptyList(),
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val mediaType: MediaType = MediaType.MOVIE,
    val popularity: Double = 0.0,
    val posterUrl: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
)
