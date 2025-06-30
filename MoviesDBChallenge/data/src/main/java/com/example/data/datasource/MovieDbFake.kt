package com.example.data.datasource

import com.example.data.getPosterUrl
import com.example.domain.enums.ImageSize
import com.example.domain.models.CastModel
import com.example.domain.models.DetailedMovieModel
import com.example.domain.models.MovieCreditsModel
import com.example.domain.models.MovieGenreModel
import com.example.domain.models.MovieLanguageModel
import com.example.domain.models.MovieModel
import com.example.domain.models.MovieRecommendationsModel
import com.example.domain.models.MoviesPageModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieDbFake : MovieRepository {

    private companion object {
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
            releaseDate = "25/05/2026",
            title = "My Favorite Movie",
            video = false,
            voteAverage = 85.0,
            voteCount = 2000,
        )
        val oldMovie = MovieModel(
            adult = true,
            backdropPath = "",
            id = 1,
            genreIds = listOf(128, 13),
            originalLanguage = "English",
            originalTitle = "My Favorite Movie",
            overview = "ahwuihdauiwheuiaheuhaisueh auisheauishe uasheuahsieuhase aushei",
            popularity = 70.0,
            posterUrl = "https://image.tmdb.org/t/p/w500/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
            releaseDate = "25/05/2023",
            title = "My Favorite Movie",
            video = false,
            voteAverage = 85.0,
            voteCount = 2000,
        )

        val detailedMovie = DetailedMovieModel(
            adult = true,
            id = 1,
            countries = listOf("BR", "US", "DE"),
            genres = listOf(MovieGenreModel(128, "Drama"), MovieGenreModel(13, "Comedy")),
            originalLanguage = "English",
            originalTitle = "My Favorite Movie",
            overview = "ahwuihdauiwheuiaheuhaisueh auisheauishe uasheuahsieuhase aushei",
            popularity = 70.0,
            poster = "https://image.tmdb.org/t/p/w500/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
            releaseDate = "2023-05-23",
            runtime = 89,
            languages = listOf(MovieLanguageModel("Portuguese", "Português"), MovieLanguageModel("English", "Inglês")),
            title = "My Favorite Movie",
            voteAverage = 85.0,
            voteCount = 2000,
        )

        val castModel = CastModel(
            id = 1,
            castId = 0,
            character = "This Character",
            name = "Artist",
            imageUrl = getPosterUrl(posterPath = "", ImageSize.LARGE)
        )

        val movieCast = MovieCreditsModel(
            id = 0,
            cast = listOf(
                castModel,
                castModel,
                castModel,
                castModel,
            )
        )

        val movieList = mutableListOf(
            oldMovie,
            oldMovie,
            oldMovie,
            oldMovie,
        )

        val upcomingList = mutableListOf(
            previewMovie,
            previewMovie,
            previewMovie,
            previewMovie,
        )

        val moviePage = MoviesPageModel(
            1,
            results = movieList
        )

        val upcomingMoviePage = MoviesPageModel(
            page = 1,
            results = upcomingList,
        )
    }

    override suspend fun getNowPlayingMovies(): Flow<MoviesPageModel> = flow {
        emit(moviePage)
    }.catch { e ->
        emit(MoviesPageModel(1, emptyList()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getPopularMovies(): Flow<MoviesPageModel> = flow {
        emit(moviePage)
    }.catch { e ->
        emit(MoviesPageModel(1, emptyList()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getUpcomingMovies(): Flow<MoviesPageModel> = flow {
        emit(upcomingMoviePage)
    }.catch { e ->
        emit(MoviesPageModel(1, emptyList()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieDetails(movieId: Int): Flow<DetailedMovieModel> = flow {
        emit(detailedMovie)
    }.catch { e ->
        emit(DetailedMovieModel())
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieRecommendations(movieId: Int): Flow<MovieRecommendationsModel> = flow<MovieRecommendationsModel> {
        emit(MovieRecommendationsModel(1, movieList))
    }.catch { e ->
        emit(MovieRecommendationsModel(1, emptyList()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMovieCredits(movieId: Int): Flow<MovieCreditsModel> = flow<MovieCreditsModel> {
        emit(movieCast)
    }.catch { e ->
        emit(MovieCreditsModel())
    }.flowOn(Dispatchers.IO)
}
