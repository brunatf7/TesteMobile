package com.labtecs.bruna.testemobile.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TMDbImageService {

    public static final String BASE_URL = "https://image.tmdb.org/t/p/original/";


    @GET("{image}")
    Call<ResponseBody> image(@Path("image") String image);
}
