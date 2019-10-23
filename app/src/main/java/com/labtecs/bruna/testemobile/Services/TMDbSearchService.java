package com.labtecs.bruna.testemobile.Services;

import com.labtecs.bruna.testemobile.Global;
import com.labtecs.bruna.testemobile.Objects.Popular;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbSearchService {
    public static final String BASE_URL = "https://api.themoviedb.org/3/search/";


    @GET("movie?language=pt-BR&api_key=" + Global.API_KEY)
    Call<Popular> search(
            @Query("query") String query
    );


}
