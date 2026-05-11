package com.micmr0.cinemalib.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.micmr0.cinemalib.ui.details.DetailsScreen
import com.micmr0.cinemalib.ui.main.MainScreen
import com.micmr0.cinemalib.ui.main.TopBar
import com.micmr0.cinemalib.ui.movies.MoviesViewModel
import com.micmr0.cinemalib.ui.settings.SettingsScreen
import com.micmr0.cinemalib.ui.settings.SettingsViewModel

@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    settingsViewModel: SettingsViewModel,
    moviesViewModel: MoviesViewModel
) {
    val moviesUiState by moviesViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "/") {
        composable(
            route = "/settings",
        ) {
            SettingsScreen(
                settingsViewModel,
                onThemeChange = {
                    settingsViewModel.setThemePreference(it)
                },
                backAction = {
                    navController.popBackStack()
                    navController.navigate("/")
                }
            )
        }
        composable("/") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                TopBar(navController = navController, settingsViewModel = settingsViewModel)
                MainScreen(settingsViewModel, moviesUiState, navController)
            }
        }
        composable(
            route = "/movie/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            DetailsScreen(
                backAction = {
                    navController.popBackStack()
                    navController.navigate("/")
                }
            )
        }
    }
}




