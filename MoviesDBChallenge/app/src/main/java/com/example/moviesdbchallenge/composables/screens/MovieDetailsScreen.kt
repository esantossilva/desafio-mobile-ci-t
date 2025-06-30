package com.example.moviesdbchallenge.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviesdbchallenge.R
import com.example.moviesdbchallenge.composables.components.CardType
import com.example.moviesdbchallenge.composables.components.CastList
import com.example.moviesdbchallenge.composables.components.CountryList
import com.example.moviesdbchallenge.composables.components.DetailPosterBackground
import com.example.moviesdbchallenge.composables.components.ExpandableTextCard
import com.example.moviesdbchallenge.composables.components.GenreList
import com.example.moviesdbchallenge.composables.components.ItemSubtitle
import com.example.moviesdbchallenge.composables.components.ItemTitle
import com.example.moviesdbchallenge.composables.components.LoadingScreen
import com.example.moviesdbchallenge.composables.components.MetadataInfo
import com.example.moviesdbchallenge.composables.components.MoviesList
import com.example.moviesdbchallenge.composables.components.VerticalSpacer
import com.example.moviesdbchallenge.composables.components.getBackgroundBrush
import com.example.moviesdbchallenge.composables.components.getMovieDetailsBackground
import com.example.moviesdbchallenge.navigation.Screen
import com.example.moviesdbchallenge.viewmodels.MovieDetailsViewModel
import kotlin.math.roundToInt

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navController: NavController,
) {
    val movie by viewModel.movie.collectAsStateWithLifecycle()
    val recommendations by viewModel.recommendations.collectAsStateWithLifecycle()
    val castList by viewModel.cast.collectAsStateWithLifecycle()
    val loadingContent by viewModel.loadingContent.collectAsStateWithLifecycle()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        DetailPosterBackground(movie = movie)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = getMovieDetailsBackground()),
        ) {
            if (loadingContent) {
                LoadingScreen()
            } else {
                VerticalSpacer(modifier = Modifier.weight(0.35f))

                Column(
                    modifier = Modifier
                        .weight(0.65f)
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_4))
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_3))
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                        ) {
                            ItemTitle(
                                text = movie.title,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize
                            )

                            if (movie.originalTitle != movie.title) {
                                VerticalSpacer()

                                ItemSubtitle(
                                    text = movie.originalTitle,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                )
                            }
                        }

                        CountryList(countries = movie.countries)
                    }

                    VerticalSpacer(size = dimensionResource(R.dimen.padding_4))

                    MetadataInfo(
                        popularity = movie.popularity,
                        releaseYear = getMovieYear(movie.releaseDate),
                        runtime = movie.runtime,
                        score = getScore(movie.voteAverage),
                        votesNumber = movie.voteCount,
                    )

                    VerticalSpacer(size = dimensionResource(R.dimen.padding_4))

                    GenreList(genres = movie.genres.map { it.name })

                    VerticalSpacer(size = dimensionResource(R.dimen.padding_5))

                    ItemTitle(
                        text = stringResource(R.string.cast_section_label),
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    )

                    VerticalSpacer(size = dimensionResource(R.dimen.padding_5))

                    CastList(castList = castList)

                    VerticalSpacer(size = dimensionResource(R.dimen.padding_5))

                    ItemTitle(
                        text = stringResource(R.string.overview_section_label),
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    )

                    VerticalSpacer(size = dimensionResource(R.dimen.padding_4))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        ExpandableTextCard(text = movie.overview)
                    }

                    VerticalSpacer(size = dimensionResource(R.dimen.padding_5))

                    ItemTitle(
                        text = stringResource(R.string.similar_titles_section_label),
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    )

                    MoviesList(
                        movieList = recommendations.results,
                        cardType = CardType.POSTER,
                        onClick = { movieId ->
                            navController.navigate(Screen.MovieDetails.createRoute(movieId))
                        }
                    )

                    VerticalSpacer(modifier = Modifier.navigationBarsPadding())
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview
fun MovieDetailsScreenPreview() {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = getBackgroundBrush())
    ) {
        MovieDetailsScreen(movieId = 911430, navController = navController)
    }
}

private fun getMovieYear(releaseDate: String) =
    releaseDate.split("-").first()

private fun getScore(voteScore: Double): Int =
    (voteScore * 10).roundToInt()
