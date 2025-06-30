package com.example.data.dto

import com.example.domain.models.MovieRecommendations
import com.google.gson.annotations.SerializedName

data class MovieRecommendationsDto (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
)

fun MovieRecommendationsDto.toMovieRecommendations() =
    MovieRecommendations(page = page, results = results.map { it.toMovie() })
