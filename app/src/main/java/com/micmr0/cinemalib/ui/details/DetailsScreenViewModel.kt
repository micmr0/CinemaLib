package com.micmr0.cinemalib.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micmr0.cinemalib.data.MoviesRepository
import com.micmr0.cinemalib.data.SettingsRepository
import com.micmr0.cinemalib.themoviedb.details_response.DetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository,
    private val appSettingsRepository: SettingsRepository,
) : ViewModel() {

    private val movieFlow = savedStateHandle.getStateFlow<Int?>("id", null).map {
        if (it != null) {
            Log.d("onMovieSelected", "Movie id is: $it")

            moviesRepository.fetchMovieDetails(appSettingsRepository.getLanguage().first(), id = it)
        } else {
            null
        }
    }

    val detailsLoadingState = movieFlow.map { movie ->
        if (movie != null) {
            DetailsLoadingState.Success(movie = movie)
        } else {
            DetailsLoadingState.NotFound
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        DetailsLoadingState.Loading
    )
}

sealed interface DetailsLoadingState {
    data object Loading : DetailsLoadingState
    data object NotFound : DetailsLoadingState
    class Success(val movie: DetailsResponse) : DetailsLoadingState
}
