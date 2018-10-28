package com.avinashsharma.codetoarttest.util

import com.avinashsharma.codetoarttest.model.MovieDetails
import com.avinashsharma.codetoarttest.model.MoviesResponse
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


interface ApiCallInterface {
    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<MovieDetails>

    @GET("movie/{id}/images")
    fun getMovieDetailsImages(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<MoviesResponse>
}