package com.labtecs.bruna.testemobile.Services;

import com.labtecs.bruna.testemobile.Global;
import com.labtecs.bruna.testemobile.Objects.Movie;
import com.labtecs.bruna.testemobile.Objects.Popular;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDbMovieService {
   public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

   @GET("popular?page=1&language=pt-BR&api_key=" + Global.API_KEY)
   Call<Popular> list();


   @GET("{id}?language=pt-BR&api_key=" + Global.API_KEY)
   Call<Movie> show(@Path("id") int id);

}
