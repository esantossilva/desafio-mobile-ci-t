package com.example.data.dto

import com.example.domain.models.MovieRecommendationsModel
import com.google.gson.annotations.SerializedName

data class MovieRecommendationsDto (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)

fun MovieRecommendationsDto.toMovieRecommendations() =
    MovieRecommendationsModel(page = page, results = results.map { it.toMovie() })
