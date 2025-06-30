package com.example.usecases

import com.example.domain.models.MovieRecommendationsModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieRecommendationsUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<MovieRecommendationsModel> =
        repository.getMovieRecommendations(movieId)
}
