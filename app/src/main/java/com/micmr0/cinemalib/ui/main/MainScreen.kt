package com.micmr0.cinemalib.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.micmr0.cinemalib.R
import com.micmr0.cinemalib.ui.movies.CategoryRow
import com.micmr0.cinemalib.ui.movies.MoviesUiState
import com.micmr0.cinemalib.ui.settings.SettingsViewModel

@Composable
fun MainScreen(
    settingsViewModel: SettingsViewModel,
    moviesUiState: MoviesUiState,
    navController: NavHostController
) {
    CategoryRow(
        isDark = settingsViewModel.isDark(),
        rowTitle = stringResource(R.string.most_popular),
        rowMovies = moviesUiState.popularMovies,
        movieCardWidth = 180.dp,
        onMovieSelected = {
            navController.navigate("/movie/${it.id}")
        }
    )
    CategoryRow(
        isDark = settingsViewModel.isDark(),
        rowTitle = stringResource(R.string.top_rated),
        rowMovies = moviesUiState.topRatedMovies,
        movieCardWidth = 160.dp,
        onMovieSelected = {
            navController.navigate("/movie/${it.id}")
        }
    )
    CategoryRow(
        isDark = settingsViewModel.isDark(),
        rowTitle = stringResource(R.string.upcoming),
        rowMovies = moviesUiState.upcomingMovies,
        movieCardWidth = 140.dp,
        onMovieSelected = {
            navController.navigate("/movie/${it.id}")
        }
    )
}