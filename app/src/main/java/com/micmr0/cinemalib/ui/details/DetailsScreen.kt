package com.micmr0.cinemalib.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.micmr0.cinemalib.R

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel(),
    backAction: () -> Unit,
) {
    val state by detailsScreenViewModel.detailsLoadingState.collectAsStateWithLifecycle()

    when (val s = state) {
        is DetailsLoadingState.Success -> Details(
            backAction = backAction,
            movie = s.movie,
            modifier = modifier
        )

        is DetailsLoadingState.NotFound -> NotFound(
            backAction = backAction,
            modifier = modifier.fillMaxSize()
        )

        else -> Loading(modifier = modifier.fillMaxSize())
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = stringResource(R.string.loading), style = MaterialTheme.typography.displayMedium)

    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NotFound(backAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text(text = "Not found", style = MaterialTheme.typography.displayMedium)
            Button(onClick = backAction) {
                Text(text = "Back to the previous screen")
            }
        }
    }
}
