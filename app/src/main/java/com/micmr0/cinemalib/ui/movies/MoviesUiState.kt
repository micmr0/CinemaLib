package com.micmr0.cinemalib.ui.movies

import com.micmr0.cinemalib.themoviedb.response.Results

data class MoviesUiState(
    var popularMovies : List<Results> =  emptyList(),
    var topRatedMovies : List<Results> =  emptyList(),
    var upcomingMovies : List<Results> =  emptyList(),
)