package com.micmr0.cinemalib.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.micmr0.cinemalib.LanguagePickerDialog
import com.micmr0.cinemalib.MainActivity
import com.micmr0.cinemalib.R
import com.micmr0.cinemalib.data.SettingsRepository

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel,
    onThemeChange: (SettingsRepository.ThemePreference) -> Unit,
    backAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val currentLanguage by settingsViewModel.currentLanguage.collectAsState()
    val currentTheme by settingsViewModel.themePreference.collectAsState()

    var showLanguageDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(20.dp), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(400.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (showLanguageDialog) {
                LanguagePickerDialog(
                    currentLanguage = currentLanguage,
                    onSelect = { lang ->
                        settingsViewModel.onLanguageChange(lang) {
                            (context as? MainActivity)?.recreate()
                        }
                        showLanguageDialog = false
                    },
                    onDismiss = { showLanguageDialog = false }
                )
            }

            Button(modifier = Modifier.fillMaxWidth(), onClick = { showLanguageDialog = true }) {
                Text(text = stringResource(R.string.select_language))
            }

            Text(
                stringResource(R.string.theme),
                textAlign = TextAlign.Center,
                color = if (settingsViewModel.isDark()) Color.White else Color.Black,
                modifier = Modifier.padding(top = 10.dp, start = 4.dp)
            )
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SettingsRepository.ThemePreference.entries.forEach { option ->
                    val selected = option == currentTheme

                    TextButton(
                        onClick = { onThemeChange(option) },
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = option.name.lowercase().replaceFirstChar { it.uppercase() },
                            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp), onClick = backAction
            ) {
                Text(text = stringResource(R.string.back_label), textAlign = TextAlign.Center)
            }
        }
    }
}
