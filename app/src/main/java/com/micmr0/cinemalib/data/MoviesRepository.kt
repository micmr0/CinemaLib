package com.micmr0.cinemalib.data

import android.util.Log
import com.micmr0.cinemalib.themoviedb.TMDBService
import com.micmr0.cinemalib.themoviedb.details_response.DetailsResponse
import com.micmr0.cinemalib.themoviedb.response.Results
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val tmdbApiService: TMDBService
) {
    suspend fun fetchMovies(lang: String, filer: MovieFilter): List<Results> {
        try {
            val response = when(filer) {
                    MovieFilter.Popular ->  tmdbApiService.getPopularMovies(lang)
                    MovieFilter.TopRated -> tmdbApiService.getTopRated(lang)
                    MovieFilter.Upcoming -> tmdbApiService.getUpcoming(lang)
                }

            if (!response.isSuccessful || response.body() == null) {
                Log.e(
                    MoviesRepository::javaClass.name,
                    "Error TMDB API: ${response.errorBody()?.string()}"
                )
            }

            val moviesData = response.body()!!
            return moviesData.results

        } catch (e: Exception) {
            Log.e(MoviesRepository::javaClass.name, "Exception: $e")
            return emptyList()
        }
    }

    suspend fun fetchMovieDetails(lang: String, id : Int): DetailsResponse {
        try {
            val response = tmdbApiService.getMovieById(id, lang)

            if (!response.isSuccessful || response.body() == null) {
                Log.e(
                    MoviesRepository::javaClass.name,
                    "Error TMDB API: ${response.errorBody()?.string()}"
                )
            }

            val movieData = response.body()!!
            return movieData

        } catch (e: Exception) {
            Log.e(MoviesRepository::javaClass.name, "Exception: $e")
            return DetailsResponse()
        }
    }
}

sealed class MovieFilter {
    object Popular : MovieFilter()
    object TopRated : MovieFilter()
    object Upcoming : MovieFilter()
}