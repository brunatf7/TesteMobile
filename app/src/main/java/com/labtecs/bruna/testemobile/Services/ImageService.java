package com.labtecs.bruna.testemobile.Services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ImageService {

    private static final String BASE_URL = "https://image.tmdb.org/t/p/original/";

    public static void getImage(String imagePath, Callback callback){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_URL+imagePath)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
