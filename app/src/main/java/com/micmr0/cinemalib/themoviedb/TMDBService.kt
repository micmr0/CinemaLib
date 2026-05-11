package com.micmr0.cinemalib.themoviedb

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular?include_video=false")
    suspend fun getPopularMovies(
        @Query("language") lang: String,
        @Query("page") days: String = "1"
    ): Response<com.micmr0.cinemalib.themoviedb.response.Response>

    @GET("movie/top_rated?include_video=false")
    suspend fun getTopRated(
        @Query("language") lang: String,
        @Query("page") days: String = "1"
    ): Response<com.micmr0.cinemalib.themoviedb.response.Response>

    @GET("movie/upcoming?include_video=false")
    suspend fun getUpcoming(
        @Query("language") lang: String,
        @Query("page") days: String = "1"
    ): Response<com.micmr0.cinemalib.themoviedb.response.Response>

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int,
        @Query("language") lang: String
    ): Response<com.micmr0.cinemalib.themoviedb.details_response.DetailsResponse>
}


