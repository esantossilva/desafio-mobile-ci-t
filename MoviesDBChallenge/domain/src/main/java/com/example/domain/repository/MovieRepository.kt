package com.example.domain.repository

import com.example.domain.models.DetailedMovieModel
import com.example.domain.models.MovieRecommendationsModel
import com.example.domain.models.MoviesPageModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlayingMovies(): Flow<MoviesPageModel>
    suspend fun getPopularMovies(): Flow<MoviesPageModel>
    suspend fun getUpcomingMovies(): Flow<MoviesPageModel>
    suspend fun getMovieDetails(movieId: Int): Flow<DetailedMovieModel>
    suspend fun getMovieRecommendations(movieId: Int): Flow<MovieRecommendationsModel>
}