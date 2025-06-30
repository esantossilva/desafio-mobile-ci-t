package com.example.usecases

import com.example.domain.models.MoviesPageModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetUpcomingMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): Flow<MoviesPageModel> =
        repository.getUpcomingMovies()
}