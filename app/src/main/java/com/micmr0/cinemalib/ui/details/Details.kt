package com.micmr0.cinemalib.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.tv.material3.Button
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.micmr0.cinemalib.R
import com.micmr0.cinemalib.themoviedb.details_response.DetailsResponse
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat

@Composable
fun Details(
    movie: DetailsResponse, backAction: () -> Unit, modifier: Modifier = Modifier
) {
    val dateFormat: DateFormat = SimpleDateFormat("yyyy", LocalLocale.current.platformLocale)

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/original/" + movie.backdropPath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.background,
                            Color.Transparent
                        )
                    )
                )
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.details_padding))
                    .fillMaxWidth(0.5f)
            ) {
                Text(
                    text = movie.title ?: "",
                    style = MaterialTheme.typography.titleLarge,
                )
                Row {
                    Text(
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.details_text_padding)),
                        text = (dateFormat.format(Date.valueOf(movie.releaseDate))),
                        style = MaterialTheme.typography.bodySmall,
                    )

                    for (genre in movie.genres) {
                        Text(
                            modifier = Modifier.padding(top = dimensionResource(R.dimen.details_text_padding)),
                            text = " • " + genre.name,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.details_text_padding_desc)),
                    text = movie.overview ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(), onClick = backAction
                ) {
                    Text(text = stringResource(R.string.back_label), textAlign = TextAlign.Center)
                }
            }
        }
    }
}
