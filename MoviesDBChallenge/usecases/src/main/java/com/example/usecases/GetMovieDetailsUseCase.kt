package com.example.usecases

import com.example.domain.models.DetailedMovieModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<DetailedMovieModel> =
        repository.getMovieDetails(movieId)
}