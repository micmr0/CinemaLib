package com.micmr0.cinemalib.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.tv.material3.Text
import com.micmr0.cinemalib.R
import com.micmr0.cinemalib.ui.settings.SettingsViewModel

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    settingsViewModel: SettingsViewModel
) {
    Box(
        modifier = modifier.wrapContentSize()
    ) {
        val accentColor =
            if (settingsViewModel.isDark()) colorResource(R.color.white) else colorResource(
                R.color.black
            )
        IconButton(
            modifier = Modifier,
            onClick = { navController.navigate("/settings") },
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                tint = accentColor,
                contentDescription = "settings"
            )
        }
        Text(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(2.dp),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.app_name),
            color = accentColor,
            style = TextStyle(
                fontSize = 28.sp,
                shadow = Shadow(
                    color = Color.Gray, offset = Offset(5.0f, 5.0f), blurRadius = 2f
                )
            )
        )
    }
}