package com.micmr0.cinemalib.ui.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micmr0.cinemalib.data.MovieFilter
import com.micmr0.cinemalib.data.MoviesRepository
import com.micmr0.cinemalib.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    private suspend fun currentLang(): String =
        settingsRepository.getLanguage().first()

    init {
       fetchData()
    }

    fun fetchData() {
        fetchPopularMovies()
        fetchTopRated()
        fetchUpcoming()
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            val lang = currentLang()

            try {
                val movies = moviesRepository.fetchMovies(
                    lang,
                    MovieFilter.Popular
                )

                _uiState.update { moviesUiState -> moviesUiState.copy(popularMovies = movies) }

            } catch (e: Exception) {
                Log.e(MoviesViewModel::javaClass.name, "Exception: $e")
            }
        }
    }

    fun fetchTopRated() {
        viewModelScope.launch {
            val lang = currentLang()

            try {
                val movies = moviesRepository.fetchMovies(
                    lang,
                    MovieFilter.TopRated
                )
                _uiState.update { moviesUiState -> moviesUiState.copy(topRatedMovies = movies) }

            } catch (e: Exception) {
                Log.e(MoviesViewModel::javaClass.name, "Exception: $e")
            }
        }
    }

    fun fetchUpcoming() {
        viewModelScope.launch {
            val lang = currentLang()

            try {
                val movies = moviesRepository.fetchMovies(
                    lang,
                    MovieFilter.Upcoming
                )
                _uiState.update { moviesUiState -> moviesUiState.copy(upcomingMovies = movies) }

            } catch (e: Exception) {
                Log.e(MoviesViewModel::javaClass.name, "Exception: $e")
            }
        }
    }
}