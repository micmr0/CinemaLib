package com.micmr0.cinemalib

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.micmr0.cinemalib.di.SettingsEntryPoint
import com.micmr0.cinemalib.ui.App
import com.micmr0.cinemalib.ui.movies.MoviesViewModel
import com.micmr0.cinemalib.ui.settings.SettingsViewModel
import com.micmr0.cinemalib.ui.theme.CinemaLibTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.util.Locale
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels()
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun attachBaseContext(newBase: Context) {
        val entryPoint = EntryPointAccessors.fromApplication(
            newBase.applicationContext,
            SettingsEntryPoint::class.java
        )

        val repo = entryPoint.settings()
        val lang = runBlocking { repo.getLanguage().first() }
        val locale = Locale.forLanguageTag(lang)
        val updated = newBase.withLocale(locale)

        super.attachBaseContext(updated)
    }

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val darkMode = settingsViewModel.isDark()
            CinemaLibTheme(darkMode) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = if (darkMode) colorResource(R.color.black_less) else colorResource(R.color.white_less),
                    shape = RectangleShape
                ) {
                    App(
                        settingsViewModel = settingsViewModel,
                        moviesViewModel = moviesViewModel
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        moviesViewModel.fetchData()
    }
}
