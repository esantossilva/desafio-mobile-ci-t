package com.example.data.datasource

import com.example.data.api.MovieDbApi
import com.example.data.dto.toDetailedMovieModel
import com.example.data.dto.toMovieCredits
import com.example.data.dto.toMovieRecommendations
import com.example.data.dto.toMoviesPage
import com.example.domain.models.DetailedMovieModel
import com.example.domain.models.MovieCreditsModel
import com.example.domain.models.MovieRecommendationsModel
import com.example.domain.models.MoviesPageModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieDbWebClient(
    private val api: MovieDbApi,
    private val apiKey: String,
): MovieRepository {

    override suspend fun getNowPlayingMovies(): Flow<MoviesPageModel> = flow {
        val nowPlayingMovies = api.getNowPlayingMovies(apiKey).toMoviesPage()
        emit(nowPlayingMovies)
    }.catch {e ->
        emit(MoviesPageModel(1, emptyList()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getPopularMovies(): Flow<MoviesPageModel> = flow {
        val popularMovies = api.getPopularMovies(apiKey).toMoviesPage()
        emit(popularMovies)
    }.catch { e ->
        emit(MoviesPageModel(1, emptyList()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getUpcomingMovies(): Flow<MoviesPageModel> = flow {
        val upcomingMovies = api.getUpcomingMovies(apiKey).toMoviesPage()
        emit(upcomingMovies)
    }.catch { e ->
        emit(MoviesPageModel(1, emptyList()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieDetails(movieId: Int): Flow<DetailedMovieModel> = flow {
        val detailedMovie = api.getMovieDetails(movieId, apiKey).toDetailedMovieModel()
        emit(detailedMovie)
    }.catch { e ->
        emit(DetailedMovieModel())
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieRecommendations(movieId: Int): Flow<MovieRecommendationsModel> = flow {
        val movieRecommendations =
            api.getMovieRecommendations(movieId, apiKey).toMovieRecommendations()
        emit(movieRecommendations)
    }.catch { e ->
        emit(MovieRecommendationsModel(1, emptyList()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieCredits(movieId: Int): Flow<MovieCreditsModel> = flow {
        val movieCredits = api.getMovieCredits(movieId, apiKey).toMovieCredits()
        emit(movieCredits)
    }.catch {
        emit(MovieCreditsModel())
    }.flowOn(Dispatchers.IO)
}
