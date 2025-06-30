package com.example.usecases

import com.example.domain.models.MovieCreditsModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieCreditsUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(movieId: Int): Flow<MovieCreditsModel> =
        repository.getMovieCredits(movieId)
}
