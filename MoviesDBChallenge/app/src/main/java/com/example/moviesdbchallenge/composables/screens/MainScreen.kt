package com.example.moviesdbchallenge.composables.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.datasource.MovieDbFake
import com.example.moviesdbchallenge.R
import com.example.moviesdbchallenge.composables.components.LoadingScreen
import com.example.moviesdbchallenge.composables.components.MoviesGrid
import com.example.moviesdbchallenge.composables.components.MoviesList
import com.example.moviesdbchallenge.composables.components.SectionTitle
import com.example.moviesdbchallenge.composables.components.SegmentedControl
import com.example.moviesdbchallenge.composables.components.getBackgroundBrush
import com.example.moviesdbchallenge.navigation.Screen
import com.example.moviesdbchallenge.viewmodels.MoviesViewModel
import com.example.usecases.GetNowPlayingMoviesUseCase
import com.example.usecases.GetPopularMoviesUseCase
import com.example.usecases.GetUpcomingMoviesUseCase

@Composable
fun MainScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    navController: NavController,
) {
    val popularMoviesList by viewModel.moviesList.collectAsStateWithLifecycle()
    val nowPlayingMoviesList by viewModel.nowPlayingMovies.collectAsStateWithLifecycle()
    val loadingContent by viewModel.loadingContent.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = getBackgroundBrush(),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (loadingContent) {
            LoadingScreen()
        }
        else {
            MoviesList(
                movieList = nowPlayingMoviesList,
                listLabel = stringResource(R.string.now_playing_label),
                onClick = { movieId ->
                    navController.navigate(Screen.MovieDetails.createRoute(movieId))
                }
            )

            SectionTitle(
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_2)),
                text = stringResource(R.string.popular_movies_label)
            )

            Row(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_4)),
                horizontalArrangement = Arrangement.Center,
            ) {
                SegmentedControl(
                    items = listOf(
                        stringResource(R.string.main_selector_all),
                        stringResource(R.string.main_selector_upcoming),
                    ),
                    useFixedWidth = true,
                    itemWidth = 120.dp,
                    onItemSelection = {
                        viewModel.setShowUpcomingMoviesEnabled(it == 1)
                    },
                )
            }

            MoviesGrid(
                movieList = popularMoviesList.results,
                onClick = { movieId ->
                    navController.navigate(Screen.MovieDetails.createRoute(movieId))
                }
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
@Preview
fun MainScreenPreview() {
    val loading = remember { mutableStateOf(false) }

    val repository = MovieDbFake()

    val navController = rememberNavController()

    val movieViewModel = MoviesViewModel(
        GetPopularMoviesUseCase(repository),
        GetNowPlayingMoviesUseCase(repository),
        GetUpcomingMoviesUseCase(repository),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainScreen(viewModel = movieViewModel, navController = navController)
    }
}
