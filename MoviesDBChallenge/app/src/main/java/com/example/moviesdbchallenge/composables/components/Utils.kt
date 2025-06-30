package com.example.moviesdbchallenge.composables.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.moviesdbchallenge.R

@Composable
internal fun getBackgroundBrush(
    colorList: List<Color> = listOf(
        colorResource(R.color.background_900),
        colorResource(R.color.background_1200),
    )
) = Brush.linearGradient(colors = colorList,)

@Composable
internal fun getVerticalGradientBackground(
    colorList: List<Color> = listOf(
        colorResource(R.color.background_900),
        colorResource(R.color.background_1200),
    )
) = Brush.verticalGradient(colors = colorList,)

@Composable internal fun getMovieDetailsBackground() =
    getVerticalGradientBackground(
        listOf(
            Color.Transparent,
            colorResource(R.color.background_1200),
            colorResource(R.color.grey_1200),
        )
    )

fun getFlagEmoji(country: String): String {
    val firstLetter = Character.codePointAt(country, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(country, 1) - 0x41 + 0x1F1E6

    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

fun getReleaseDateFormated(releaseDate: String): String {
    val dateArray = releaseDate.split("-").reversed()
    return dateArray.joinToString("/")
}

fun getRuntimeString(runtimeMin: Int): String {
    val hours = runtimeMin / 60
    val minutes = runtimeMin % 60

    val hStr = if (hours > 0) "${hours}h " else ""
    val mStr = if (minutes > 0) "${minutes}m" else ""

    return "$hStr$mStr"
}
