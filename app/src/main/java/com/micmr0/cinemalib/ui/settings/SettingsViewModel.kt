package com.micmr0.cinemalib.ui.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micmr0.cinemalib.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Locale

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settings: SettingsRepository
) : ViewModel() {

    val currentLanguage = settings.getLanguage().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = Locale.getDefault().language
    )

    val themePreference = settings.themePreference().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SettingsRepository.ThemePreference.SYSTEM
    )

    fun onLanguageChange(lang: String, onFinished: () -> Unit) {
        viewModelScope.launch {
            settings.setLanguage(lang)

            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(lang)
            )
            onFinished()
        }
    }


    fun setThemePreference(pref: SettingsRepository.ThemePreference) {
        viewModelScope.launch {
            settings.setThemePreference(pref)
        }
    }

    @Composable
    fun isDark() : Boolean {
        val themePreference = themePreference.collectAsState()

        return when (themePreference.value) {
            SettingsRepository.ThemePreference.SYSTEM -> isSystemInDarkTheme()
            SettingsRepository.ThemePreference.DARK -> true
            SettingsRepository.ThemePreference.LIGHT -> false
        }
    }
}