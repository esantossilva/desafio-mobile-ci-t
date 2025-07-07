package com.example.data.dto

import com.example.data.getPosterUrl
import com.example.domain.enums.ImageSize
import com.example.domain.models.CastModel
import com.example.domain.models.MovieCreditsModel
import com.google.gson.annotations.SerializedName

data class MovieCreditsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<CastModelDto>,
    @SerializedName("crew") val crew: List<CrewModelDto>,
)

data class CastModelDto(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("gender") val gender: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("known_for_department") val knownForDepartment: String,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("cast_id") val castId: Int,
    @SerializedName("character") val character: String,
    @SerializedName("credit_id") val creditId: String,
    @SerializedName("order") val order: Int,
)

data class CrewModelDto(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("gender") val gender: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("known_for_department") val knownForDepartment: String,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("credit_id") val creditId: String,
    @SerializedName("department") val department: String,
    @SerializedName("job") val job: String,
)

fun CastModelDto.toCastModel() =
    CastModel(
        id = id,
        castId = castId,
        name = name,
        character = character,
        imageUrl = profilePath?.let { getPosterUrl(profilePath, ImageSize.LARGE) } ?: "",
    )

fun MovieCreditsDto.toMovieCredits() =
    MovieCreditsModel(id, cast.getPrimaryCast().map { it.toCastModel() })

fun List<CastModelDto>.getPrimaryCast(castSize: Int = 10) =
    if (this.size > castSize) this.subList(0, castSize - 1) else this
