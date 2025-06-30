package com.example.domain.models

data class DetailedMovieModel(
    val adult: Boolean = false,
    val genres: List<MovieGenreModel> = emptyList(),
    val id: Int = 0,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster: String = "",
    val countries: List<String> = emptyList(),
    val releaseDate: String = "",
    val runtime: Int = 0,
    val languages: List<MovieLanguageModel> = emptyList(),
    val title: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
)
