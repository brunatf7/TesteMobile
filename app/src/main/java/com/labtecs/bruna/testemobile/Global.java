package com.labtecs.bruna.testemobile;

import com.labtecs.bruna.testemobile.Services.TMDbMovieService;
import com.labtecs.bruna.testemobile.Services.TMDbSearchService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Global {

    public static final String API_KEY = "67444c1f7e8ad95e61376373d43b94ee";

    public static final Retrofit retrofitMovie = new Retrofit.Builder()
            .baseUrl(TMDbMovieService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final Retrofit retrofitSearch = new Retrofit.Builder()
            .baseUrl(TMDbSearchService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
