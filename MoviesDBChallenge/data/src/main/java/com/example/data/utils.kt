package com.example.data

import com.example.domain.enums.ImageSize

fun getPosterUrl(posterPath: String, imageSize: ImageSize) =
    "https://image.tmdb.org/t/p/${getImageSize(imageSize)}$posterPath"

fun getImageSize(imageSize: ImageSize): String =
    when (imageSize) {
        ImageSize.SMALL -> "w185"
        ImageSize.MEDIUM -> "w342"
        ImageSize.LARGE -> "w500"
        ImageSize.HIGHEST -> "w780"
    }
