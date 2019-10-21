package com.labtecs.bruna.testemobile.Services;

import com.labtecs.bruna.testemobile.Global;
import com.labtecs.bruna.testemobile.Objects.Movie;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDbSearchService {
    public static final String BASE_URL = "https://image.tmdb.org/t/p/original/";


    @GET("movie?query={query}&language=pt-BR&api_key=" + Global.API_KEY)
    Call<Movie> search(@Path("query") String query);


}
