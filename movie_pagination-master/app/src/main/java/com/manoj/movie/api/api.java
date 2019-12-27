package com.manoj.movie.api;

import com.manoj.movie.api.pojo.moviedetailss;
import com.manoj.movie.api.pojo.movielist;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface api {
    String baseurl = "https://api.themoviedb.org/3/";
    //String imageurl = "https://image.tmdb.org/t/p/w200";
    String imageurl ="https://image.tmdb.org/t/p/original";

    @GET("movie/now_playing")
    Call<movielist> getmovies(
            @Query("api_key") String api_key,
            @Query("page") int page
    );



    @GET
    Call<moviedetailss> getmovieDetails(
            @Url String url,
            @Query("api_key") String api_key
    );



}
