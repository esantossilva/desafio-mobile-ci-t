package com.example.data.dto

import com.example.data.getPosterUrl
import com.example.domain.enums.ImageSize
import com.example.domain.enums.MediaType
import com.example.domain.models.MovieModel
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("media_type") val mediaType: String?,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
)

fun MovieDto.toMovie(): MovieModel =
    MovieModel(
        adult,
        backdropPath ?: "",
        genreIds,
        id,
        originalLanguage,
        originalTitle,
        overview,
        mediaType?.let { getMediaType(it) } ?: MediaType.MOVIE,
        popularity,
        posterPath?.let { getPosterUrl(it, ImageSize.LARGE) } ?: "",
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount,
    )

private fun getMediaType(mediaType: String): MediaType =
    when (mediaType) {
        "tv" -> MediaType.TV
        "person" -> MediaType.PERSON
        else -> MediaType.MOVIE
    }
