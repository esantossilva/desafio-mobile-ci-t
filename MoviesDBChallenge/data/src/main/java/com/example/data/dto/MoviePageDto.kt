package com.example.data.dto

import com.example.domain.models.MoviesPageModel
import com.google.gson.annotations.SerializedName

data class MoviesPageDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)

data class DatedMoviesPageDto(
    @SerializedName("dates") val dates: MovieDatesDto,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)

data class MovieDatesDto(
    @SerializedName("maximum") val maximum: String,
    @SerializedName("minimum") val minimum: String,
)

fun MoviesPageDto.toMoviesPage(): MoviesPageModel = MoviesPageModel(page, results.map { it.toMovie() })

fun DatedMoviesPageDto.toMoviesPage(): MoviesPageModel = MoviesPageModel(page, results.map { it.toMovie() })
