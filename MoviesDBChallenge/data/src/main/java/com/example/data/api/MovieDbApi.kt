package com.example.data.api

import com.example.data.dto.DatedMoviesPageDto
import com.example.data.dto.MovieCreditsDto
import com.example.data.dto.MovieDetailsDto
import com.example.data.dto.MovieRecommendationsDto
import com.example.data.dto.MoviesPageDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {
    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MoviesPageDto

    @GET("now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apiKey: String): DatedMoviesPageDto

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): DatedMoviesPageDto

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieDetailsDto

    @GET("{movie_id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieRecommendationsDto

    @GET("{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieCreditsDto
}