package com.micmr0.cinemalib.ui.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.micmr0.cinemalib.themoviedb.response.Results
import com.micmr0.cinemalib.R

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier,
    isDark: Boolean,
    rowTitle: String,
    rowMovies: List<Results>,
    movieCardWidth: Dp,
    onMovieSelected: (Results) -> Unit = {}
) {
    Card(
        modifier
            .wrapContentSize()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = getCardBackground(isDark))
    ) {
        val textColor = if (isDark) colorResource(R.color.white) else colorResource(R.color.black)

        Column(
            modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = rowTitle,
                style = MaterialTheme.typography.titleSmall,
                color = textColor,
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(rowMovies) { movie ->
                    MovieCard(
                        isDark,
                        movie = movie,
                        movieCardWidth,
                        onClick = { onMovieSelected(movie) })
                }
            }
        }
    }
}

@Composable
fun getCardBackground(isDark: Boolean): Color {
    return if (isDark) {
        colorResource(R.color.category_dark)
    } else {
        colorResource(R.color.category_light)
    }
}