package com.example.usecases

import com.example.domain.models.MovieRecommendations
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieRecommendationsUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<MovieRecommendations> =
        repository.getMovieRecommendations(movieId)
}
