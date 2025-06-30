package com.example.moviesdbchallenge.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CastModel
import com.example.domain.models.DetailedMovieModel
import com.example.domain.models.MovieRecommendationsModel
import com.example.usecases.GetMovieCreditsUseCase
import com.example.usecases.GetMovieDetailsUseCase
import com.example.usecases.GetMovieRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieRecommendationsUseCase: GetMovieRecommendationsUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    private val _loadingContent = MutableStateFlow(true)
    val loadingContent: StateFlow<Boolean> = _loadingContent

    private val _movie = MutableStateFlow(DetailedMovieModel())
    val movie: StateFlow<DetailedMovieModel> = _movie

    private val _recommendations = MutableStateFlow(MovieRecommendationsModel())
    val recommendations: StateFlow<MovieRecommendationsModel> = _recommendations

    private val _cast = MutableStateFlow(emptyList<CastModel>())
    val cast: StateFlow<List<CastModel>> = _cast

    init {
        getMovieDetails()
        getMovieRecommendations()
        getMovieCredits()
    }

    private fun getMovieDetails() {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).collect { movie ->
                _movie.value = movie
            }
        }
    }

    private fun getMovieRecommendations() {
        viewModelScope.launch {
            getMovieRecommendationsUseCase(movieId).collect { recommendations ->
                _recommendations.value = recommendations
            }
        }
    }

    private fun getMovieCredits() {
        viewModelScope.launch {
            getMovieCreditsUseCase(movieId).collect { credits ->
                _cast.value = credits.cast

                if (credits.cast.isNotEmpty()) _loadingContent.value = false
            }
        }
    }
}
