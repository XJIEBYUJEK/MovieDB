package com.example.moviedb.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Single
import com.example.moviedb.data.responses.CreditsResponse
import com.example.moviedb.data.responses.MovieDetailsResponse
import com.example.moviedb.data.responses.MoviesResponse
import com.example.moviedb.data.responses.TvShowsResponse
import com.example.moviedb.ui.getLanguage

interface MovieApiInterface {

    @GET("movie/now_playing")
    fun getNowPlaying(@Query("api_key") apiKey: String, @Query("language") language: String = getLanguage()): Single<MoviesResponse>

    @GET("discover/tv")
    fun getPopularTvShows(@Query("api_key") apiKey: String, @Query("language") language: String = getLanguage()): Single<TvShowsResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String, @Query("language") language: String = getLanguage()): Single<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("language") language: String = getLanguage()): Single<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String, @Query("language") language: String = getLanguage()): Single<MovieDetailsResponse>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int, @Query("api_key") apiKey: String, @Query("language") language: String = getLanguage()): Single<CreditsResponse>
}
