package com.micmr0.cinemalib.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private suspend fun <T> saveValue(key: Preferences.Key<T>, value: T) {
        withContext(Dispatchers.IO) {
            dataStore.updateData { preferences ->
                preferences.toMutablePreferences().apply {
                    this[key] = value
                }
            }
        }
    }

    private fun <T> getValueFlow(key: Preferences.Key<T>, default: T): Flow<T> {
        return dataStore.data.map { preferences -> preferences[key] ?: default }
    }

    fun getLanguage(): Flow<String> =
        dataStore.data.map { prefs ->
            prefs[PreferencesKeys.APP_LANGUAGE_KEY] ?: Locale.getDefault().language
        }

    suspend fun setLanguage(lang: String) {
        dataStore.updateData { prefs ->
            prefs.toMutablePreferences().apply {
                this[PreferencesKeys.APP_LANGUAGE_KEY] = lang
            }
        }
    }

    fun themePreference(): Flow<ThemePreference> =
        getValueFlow(PreferencesKeys.THEME_PREFERENCE_KEY, ThemePreference.SYSTEM.name)
            .map { ThemePreference.fromString(it) }

    suspend fun setThemePreference(pref: ThemePreference) {
        saveValue(PreferencesKeys.THEME_PREFERENCE_KEY, pref.name)
    }

    enum class ThemePreference {
        SYSTEM, LIGHT, DARK;

        companion object {
            fun fromString(value: String?): ThemePreference =
                entries.find { it.name == value } ?: SYSTEM
        }
    }

    object PreferencesKeys {
        val APP_LANGUAGE_KEY = stringPreferencesKey("app_language")
        val THEME_PREFERENCE_KEY = stringPreferencesKey("theme_preference")
    }
}