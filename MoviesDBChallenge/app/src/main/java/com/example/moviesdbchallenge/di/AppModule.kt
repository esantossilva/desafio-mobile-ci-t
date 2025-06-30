package com.example.moviesdbchallenge.di

import com.example.data.api.MovieDbApi
import com.example.data.datasource.MovieDbWebClient
import com.example.domain.repository.MovieRepository
import com.example.moviesdbchallenge.BuildConfig
import com.example.usecases.GetMovieDetailsUseCase
import com.example.usecases.GetMovieRecommendationsUseCase
import com.example.usecases.GetNowPlayingMoviesUseCase
import com.example.usecases.GetPopularMoviesUseCase
import com.example.usecases.GetUpcomingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"

        val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val httpClient = OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun providesApiService(): MovieDbApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDbApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieRepository(): MovieRepository {
        return MovieDbWebClient(
            api = providesApiService(),
            apiKey = BuildConfig.MOVIE_DB_API_KEY,
        )
    }

    @Provides
    fun providesPopularMoviesUseCase(repository: MovieRepository) =
        GetPopularMoviesUseCase(repository)

    @Provides
    fun providesUpcomingMoviesUseCase(repository: MovieRepository) =
        GetUpcomingMoviesUseCase(repository)

    @Provides
    fun providesNowPlayingMoviesUseCase(repository: MovieRepository) =
        GetNowPlayingMoviesUseCase(repository)

    @Provides
    fun providesMovieDetailsUseCase(repository: MovieRepository) =
        GetMovieDetailsUseCase(repository)

    @Provides
    fun providesMovieRecommendations(repository: MovieRepository) =
        GetMovieRecommendationsUseCase(repository)
}
