package com.micmr0.cinemalib

import com.micmr0.cinemalib.data.MovieFilter
import com.micmr0.cinemalib.data.MoviesRepository
import com.micmr0.cinemalib.themoviedb.TMDBService
import com.micmr0.cinemalib.themoviedb.details_response.BelongsToCollection
import com.micmr0.cinemalib.themoviedb.details_response.DetailsResponse
import com.micmr0.cinemalib.themoviedb.response.Response
import com.micmr0.cinemalib.themoviedb.response.Results
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MoviesRepositoryTest {
    @Mock
    lateinit var tmdbService: TMDBService
    lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        moviesRepository = MoviesRepository(tmdbService)
    }

    @Test
    fun fetchMoviesRepository_getPopularMovies_positiveResponse() {
        runBlocking {

            val popularMoviesResponse = Response(1, getMockMovies(), 1, 2)

            Mockito.`when`(tmdbService.getPopularMovies("en")).thenReturn(
                retrofit2.Response.success(popularMoviesResponse)
            )

            val response = moviesRepository.fetchMovies("en", MovieFilter.Popular)

            assertEquals(
                response,
                popularMoviesResponse.results
            )
        }
    }

    @Test
    fun fetchMoviesRepository_getTopRated_positiveResponse() {
        runBlocking {

            val popularMoviesResponse = Response(1, getMockMovies(), 1, 2)

            Mockito.`when`(tmdbService.getTopRated("en")).thenReturn(
                retrofit2.Response.success(popularMoviesResponse)
            )

            val response = moviesRepository.fetchMovies("en", MovieFilter.TopRated)

            assertEquals(
                response,
                popularMoviesResponse.results
            )
        }
    }

    @Test
    fun fetchMoviesRepository_getUpcoming_positiveResponse() {
        runBlocking {

            val popularMoviesResponse = Response(1, getMockMovies(), 1, 2)

            Mockito.`when`(tmdbService.getUpcoming("en")).thenReturn(
                retrofit2.Response.success(popularMoviesResponse)
            )

            val response = moviesRepository.fetchMovies("en", MovieFilter.Upcoming)

            assertEquals(
                response,
                popularMoviesResponse.results
            )
        }
    }

    @Test
    fun fetchMoviesRepository_getMovieDetails_positiveResponse() {
        runBlocking {

            val movieDetailsResponse = getMockMovieDetails()

            Mockito.`when`(tmdbService.getMovieById(1, "en")).thenReturn(
                retrofit2.Response.success(movieDetailsResponse)
            )

            val response = moviesRepository.fetchMovieDetails("en", 1)

            assertEquals(
                response,
                movieDetailsResponse
            )
        }
    }
}

fun getMockMovies(): ArrayList<Results> {
    return arrayListOf(
        Results(
            false,
            "url",
            arrayListOf(1, 2, 3),
            1,
            "en",
            "Ant-Man and the Wasp: Quantumania",
            "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents...",
            8567.865,
            "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
            "2023-02-15",
            "Ant-Man and the Wasp: Quantumania",
            false,
            6.5,
            1886
        ),
        Results(
            false,
            "url",
            arrayListOf(1, 2, 3),
            2,
            "en",
            "The Super Mario Bros. Movie",
            "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are ...",
            6572.614,
            "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
            "2023-04-05",
            "The Super Mario Bros. Movie",
            false,
            7.5,
            1456
        ),
        Results(
            false,
            "url",
            arrayListOf(1, 2, 3),
            3,
            "en",
            "Shazam! Fury of the Gods",
            "Billy Batson and his foster siblings, who transform into superheroes by saying \"Shazam!\", are forced ...",
            4274.232,
            "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
            "2023-03-15",
            "Shazam! Fury of the Gods",
            false,
            6.9,
            1231
        )
    )
}

private fun getMockMovieDetails(): DetailsResponse {
    return DetailsResponse(
        false,
        "/2w4xG178RpB4MDAIfTkqAuSJzec.jpg",
        BelongsToCollection(
            10,
            "Star Wars Collection",
            "/pWVLFh4OuejTpUaDQbB1C4zoS2p.jpg",
            "/iY2ujEY2m68OTTlPFTiHub9joHS.jpg"
        ),
        11000000,
        arrayListOf(),
        "http://www.starwars.com/films/star-wars-episode-iv-a-new-hope",
        11,
        "tt0076759",
        arrayListOf(),
        "en",
        "Star Wars",
        "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire.",
        20.6912,
        "/6FfCtAuVAW8XJjZ7eWeLibRLWTw.jpg",
        arrayListOf(),
        arrayListOf(),
        "1977-05-25",
        775398007,
        121,
        arrayListOf(),
        "Released",
        "A long time ago in a galaxy far, far away...",
        "Star Wars",
        false,
        8.2,
        22061
    )
}
