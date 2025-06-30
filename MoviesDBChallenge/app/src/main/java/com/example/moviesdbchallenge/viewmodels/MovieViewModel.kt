package com.example.moviesdbchallenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.MovieModel
import com.example.domain.models.MoviesPageModel
import com.example.usecases.GetNowPlayingMoviesUseCase
import com.example.usecases.GetPopularMoviesUseCase
import com.example.usecases.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class MovieCategory {
    UPCOMING,
    POPULAR,
}

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
): ViewModel() {

    private val _loadingContent = MutableStateFlow(true)
    val loadingContent: StateFlow<Boolean> = _loadingContent

    private val _selectedCategory = MutableStateFlow(MovieCategory.POPULAR)

    @OptIn(ExperimentalCoroutinesApi::class)
    val moviesList: StateFlow<MoviesPageModel> = _selectedCategory
        .flatMapLatest { category ->
            when (category) {
                MovieCategory.UPCOMING -> getUpcomingMoviesUseCase()
                MovieCategory.POPULAR -> getPopularMoviesUseCase()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = MoviesPageModel(),
        )

    private val _nowPlayingMovies = MutableStateFlow<List<MovieModel>>(emptyList())
    val nowPlayingMovies: StateFlow<List<MovieModel>> = _nowPlayingMovies

    init {
        getNowPlayingMovies()
    }

    fun setShowUpcomingMoviesEnabled(isEnabled: Boolean) {
        _selectedCategory.value = if (isEnabled) MovieCategory.UPCOMING else MovieCategory.POPULAR
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase().collect { moviePage ->
                _nowPlayingMovies.value = moviePage.results

                if (moviePage.results.isNotEmpty()) _loadingContent.value = false
            }
        }
    }

    /** If we want to actually filter results.
    private fun isUpcomingMovie(date: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val movieDate = LocalDate.parse(date, formatter)

        val a = LocalDate.now().isBefore(movieDate)

        Log.d("movieDb", "isUpcomingMovie = $a")

        return LocalDate.now().isBefore(movieDate)
    }
     **/
}