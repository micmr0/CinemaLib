package com.micmr0.cinemalib.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.micmr0.cinemalib.R
import com.micmr0.cinemalib.themoviedb.response.Results
import java.text.DateFormat
import java.text.SimpleDateFormat
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import java.sql.Date

@Composable
fun MovieCard(
    isDark: Boolean,
    movie: Results,
    movieCardWidth: Dp,
    modifier: Modifier = Modifier,
    onClick: (Results) -> Unit = {}
) {
    Card(
        onClick = { onClick(movie) },
        modifier = modifier.padding(horizontal = 10.dp),
    ) {
        val textColor = if (isDark) colorResource(R.color.white) else colorResource(R.color.black)
        val dateFormat: DateFormat = SimpleDateFormat("yyyy", LocalLocale.current.platformLocale)

        Column(modifier = Modifier.width(movieCardWidth)) {
            Box(modifier = Modifier.width(movieCardWidth)) {
                AsyncImage(
                    model = stringResource(R.string.movie_image_url_prefix) + movie.backdropPath,
                    contentDescription = movie.overview,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    modifier = Modifier
                        .widthIn(max = movieCardWidth)
                        .aspectRatio(16f / 9f)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    textAlign = TextAlign.End,
                    text = (String.format(
                        LocalLocale.current.platformLocale, "%.1f ★", movie.voteAverage
                    )),
                    color = colorResource(R.color.white),
                    style = TextStyle(
                        fontSize = 11.sp,
                        shadow = Shadow(
                            color = Color.Black, offset = Offset(5.0f, 5.0f), blurRadius = 5f
                        )
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (isDark) colorResource(R.color.black_less) else
                            colorResource(R.color.white_less)
                    )
                    .padding(4.dp),
            ) {
                Text(
                    modifier = Modifier,
                    text = if (movie.title!!.length > 20) movie.title!!.substring(
                        0,
                        20
                    ) + "..." else movie.title!!,
                    color = textColor,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier,
                    text = (dateFormat.format(Date.valueOf(movie.releaseDate))),
                    color = textColor,
                    style = MaterialTheme.typography.labelSmall
                )

            }
        }
    }
}
