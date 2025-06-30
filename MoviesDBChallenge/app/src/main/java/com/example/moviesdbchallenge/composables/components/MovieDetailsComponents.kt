package com.example.moviesdbchallenge.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.models.DetailedMovieModel
import com.example.moviesdbchallenge.R

@Composable
fun DetailPosterBackground(
    modifier: Modifier = Modifier,
    movie: DetailedMovieModel,
    contentDescription: String = "",
    placeholder: Painter = painterResource(R.drawable.placeholder)
) {
    Column(
        modifier = modifier,
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = movie.poster,
            contentDescription = contentDescription,
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center,
            placeholder = placeholder,
            error = placeholder,
        )
    }
}

@Composable
fun MetadataInfo(
    modifier: Modifier = Modifier,
    releaseYear: String = "0000",
    runtime: Int = 0,
    score: Int = 0,
    votesNumber: Int = 0,
    popularity: Double = 0.0,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        ItemSubtitle(
            modifier = Modifier.padding(end = 8.dp),
            text = releaseYear,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        )

        DefaultVerticalDivider()

        RatingScore(
            modifier = Modifier.padding(horizontal = 8.dp),
            score = score,
            votesNumber = votesNumber,
        )

        DefaultVerticalDivider()

        ItemSubtitle(
            modifier = Modifier.padding(8.dp),
            text = getRuntimeString(runtime),
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        )

        DefaultVerticalDivider()

        PopularityScore(
            modifier = Modifier.padding(start = 8.dp),
            popularity = popularity,
        )
    }
}

@Composable
fun GenreList(
    modifier: Modifier = Modifier,
    genres: List<String>,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_3)),
    ) {
        items(genres) {
            OutlineLabel(text = it)
        }
    }
}

@Composable
fun CountryList(
    modifier: Modifier = Modifier,
    countries: List<String>,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_3)),
        contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.padding_3)),
    ) {
        items(countries) {
            ItemSubtitle(
                text = getFlagEmoji(it),
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            )
        }
    }
}

@Composable
fun RatingScore(
    modifier: Modifier = Modifier,
    score: Int = 0,
    votesNumber: Int = 0,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_star),
            contentDescription = null,
            tint = colorResource(R.color.gold_200)
        )

        HorizontalSpacer(size = 4.dp)

        ItemSubtitle(
            text = "$score ($votesNumber)",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        )
    }
}

@Composable
fun PopularityScore(
    modifier: Modifier = Modifier,
    popularity: Double = 0.0,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_popularity),
            contentDescription = null,
            tint = colorResource(R.color.orange_100)
        )

        HorizontalSpacer(size = 8.dp)

        ItemSubtitle(
            text = popularity.toString(),
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        )
    }
}

@Composable
@Preview
fun GenreListPreview() {
    GenreList(genres = listOf("Comedy", "Drama"))
}

@Composable
@Preview
fun CountryListPreview() {
    CountryList(countries = listOf("US", "ES"))
}

@Composable
@Preview
fun MetadataInfoPreview() {
    MetadataInfo()
}

@Composable
@Preview
fun RatingScorePreview() {
    RatingScore()
}

@Composable
@Preview
fun PopularityScorePreview() {
    PopularityScore()
}

@Composable
@Preview
fun DetailsPosterBackGroundPreview() {
    Box(
        modifier = Modifier.background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        DetailPosterBackground(
            movie = DetailedMovieModel(
                poster = "https://m.media-amazon.com/images/I/71KPOvu-hOL._AC_SL1351_.jpg"
            )
        )
        OutlineLabel(text = "Drama")
    }
}
