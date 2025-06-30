package com.example.moviesdbchallenge.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.models.MovieModel
import com.example.moviesdbchallenge.R
import com.example.moviesdbchallenge.ui.theme.MoviesDBChallengeTheme

enum class CardType {
    BANNER,
    POSTER
}

@Composable
fun MoviesList(
    modifier: Modifier = Modifier,
    movieList: List<MovieModel>,
    listLabel: String = "",
    onClick: (movieId: Int) -> Unit = {},
    cardType: CardType = CardType.BANNER,
) {
    val listState = rememberLazyListState()
    
    LazyRow(
        modifier = modifier,
        state = listState,
    ) {
        items(movieList) {
            when (cardType) {
                CardType.BANNER ->
                    MovieBanner(
                        modifier = Modifier
                            .padding(end = dimensionResource(R.dimen.padding_1))
                            .clickable { onClick(it.id) },
                        movie = it,
                        cardLabel = listLabel,
                    )
                CardType.POSTER ->
                    MovieItem(
                        modifier = Modifier
                            .padding(end = dimensionResource(R.dimen.padding_3))
                            .clickable { onClick(it.id) },
                        movie = it,
                    )
            }
        }
    }
}

@Composable
fun MoviesGrid(
    modifier: Modifier = Modifier,
    movieList: List<MovieModel>,
    onClick: (movieId: Int) -> Unit = {},
) {
    val listState = rememberLazyListState()

    LazyVerticalGrid(
        modifier = modifier.navigationBarsPadding(),
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(movieList) {
            MovieItem(
                modifier = Modifier.clickable { onClick(it.id) },
                movie = it
            )
        }
    }
}

@Composable
fun MovieBanner(
    modifier: Modifier = Modifier,
    movie: MovieModel? = null,
    cardLabel: String = "",
) {
    Column(
        modifier = modifier
            .height(dimensionResource(R.dimen.movie_banner_height))
            .width(dimensionResource(R.dimen.movie_banner_width))
            .background(Color.Transparent),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        movie?.let {
            MovieCard(
                movie = it,
                contentDescription = it.title,
                cardType = CardType.BANNER,
                cardLabel = cardLabel,
            )
        }
    }
}

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: MovieModel? = null,
) {
    Column(
        modifier = modifier
            .height(dimensionResource(R.dimen.movie_card_height))
            .width(dimensionResource(R.dimen.movie_card_width))
            .background(Color.Transparent)
            .clip(RoundedCornerShape(16.dp))
            .padding(dimensionResource(R.dimen.padding_2)),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        movie?.let {
            MovieCard(
                movie = it,
                contentDescription = it.title,
            )
        }
    }
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: MovieModel? = null,
    cardLabel: String = "",
    contentDescription: String = "",
    cardType: CardType = CardType.POSTER,
    placeholder: Painter = painterResource(R.drawable.placeholder),
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart,
    ) {
        movie?.let {
            AsyncImage(
                modifier = when (cardType) {
                    CardType.BANNER -> modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = 0.99F }
                        .drawWithContent {
                            val colors = listOf(
                                Color.Black,
                                Color.Transparent,
                            )
                            drawContent()
                            drawRect(
                                brush = Brush.verticalGradient(colors),
                                blendMode = BlendMode.DstIn
                            )
                        }
                    CardType.POSTER -> modifier.fillMaxSize()
                },
                model = it.posterUrl,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                placeholder = placeholder,
                error = placeholder,
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.6f)
                            ),
                        ),
                    )
                    .padding(
                        when (cardType) {
                            CardType.BANNER -> dimensionResource(R.dimen.padding_4)
                            CardType.POSTER -> dimensionResource(R.dimen.padding_3)
                        }
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
            ) {
                ItemSubtitle(text = cardLabel)

                ItemTitle(
                    text = it.title,
                    fontSize = when (cardType) {
                        CardType.BANNER -> MaterialTheme.typography.titleMedium.fontSize
                        CardType.POSTER -> MaterialTheme.typography.labelMedium.fontSize
                    },
                )
                ItemSubtitle(
                    text = getReleaseDateFormated(it.releaseDate),
                )
            }
        }
    }
}

@Composable
@Preview
fun MovieBannerPreview() {
    val previewMovie = MovieModel(
        adult = true,
        backdropPath = "",
        id = 1,
        genreIds = listOf(128, 13),
        originalLanguage = "English",
        originalTitle = "My Favorite Movie",
        overview = "ahwuihdauiwheuiaheuhaisueh auisheauishe uasheuahsieuhase aushei",
        popularity = 70.0,
        posterUrl = "https://image.tmdb.org/t/p/w500/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
        releaseDate = "25/05/2025",
        title = "My Favorite Movie",
        video = false,
        voteAverage = 85.0,
        voteCount = 2000,
    )
    MovieBanner(movie = previewMovie, cardLabel = "NOW PLAYING")
}

@Composable
@Preview
fun MovieItemPreview() {
    val previewMovie = MovieModel(
        adult = true,
        backdropPath = "",
        id = 1,
        genreIds = listOf(128, 13),
        originalLanguage = "English",
        originalTitle = "My Favorite Movie",
        overview = "ahwuihdauiwheuiaheuhaisueh auisheauishe uasheuahsieuhase aushei",
        popularity = 70.0,
        posterUrl = "https://image.tmdb.org/t/p/w500/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
        releaseDate = "25/05/2025",
        title = "My Favorite Movie",
        video = false,
        voteAverage = 85.0,
        voteCount = 2000,
    )
    MovieItem(movie = previewMovie)
}

@Composable
@Preview
fun MoviesListWidgetsPreview() {
    MoviesDBChallengeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.teal_900),
                            colorResource(R.color.black)
                        ),
                    )
                )
        ) {
            val previewMovie = MovieModel(
                adult = true,
                backdropPath = "",
                id = 1,
                genreIds = listOf(128, 13),
                originalLanguage = "English",
                originalTitle = "My Favorite Movie",
                overview = "ahwuihdauiwheuiaheuhaisueh auisheauishe uasheuahsieuhase aushei",
                popularity = 70.0,
                posterUrl = "https://image.tmdb.org/t/p/w500/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
                releaseDate = "25/05/2025",
                title = "My Favorite Movie",
                video = false,
                voteAverage = 85.0,
                voteCount = 2000,
            )

            val movieList = SnapshotStateList<MovieModel>()
            movieList.addAll(listOf(
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
                previewMovie,
            ))

           // Add widgets here
            MoviesList(movieList = movieList)
            MoviesGrid(movieList = movieList)
        }
    }
}
