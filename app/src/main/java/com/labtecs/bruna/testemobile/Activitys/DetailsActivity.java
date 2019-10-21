package com.labtecs.bruna.testemobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.labtecs.bruna.testemobile.Global;
import com.labtecs.bruna.testemobile.Objects.Movie;
import com.labtecs.bruna.testemobile.Objects.Popular;
import com.labtecs.bruna.testemobile.R;
import com.labtecs.bruna.testemobile.Services.TMDbMovieService;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        getDetailsMovie(id);

    }

    private void getDetailsMovie(int id) {
        TMDbMovieService service = Global.retrofitMovie.create(TMDbMovieService.class);

        Call<Movie> requestMovie = service.show(id);

        requestMovie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(!response.isSuccess()){

                }else{
                    movie = response.body();
                    setPageData();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void setPageData() {
        ImageView imageView = (ImageView) findViewById(R.id.imageViewDetails);
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        TextView textViewDate = (TextView) findViewById(R.id.textViewDate);
        TextView textViewOverview = (TextView) findViewById(R.id.textViewOverview);

        textViewName.setText(movie.title);
        textViewDate.setText("Lançamento em "+movie.release_date);
        textViewOverview.setText(movie.overview);
    }

}
