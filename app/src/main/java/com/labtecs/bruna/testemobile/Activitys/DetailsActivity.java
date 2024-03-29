package com.labtecs.bruna.testemobile.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.labtecs.bruna.testemobile.Global;
import com.labtecs.bruna.testemobile.Objects.Movie;
import com.labtecs.bruna.testemobile.R;
import com.labtecs.bruna.testemobile.Services.DownloadImageTask;
import com.labtecs.bruna.testemobile.Services.TMDbMovieService;

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


        int id = getIntent().getIntExtra("id", 0);

        getDetailsMovie(id);


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fabShare);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Ei, da uma olhada nesse filme! https://www.themoviedb.org/movie/"+movie.id);
                startActivity(Intent.createChooser(intent, "Compartilhar via"));
            }
        });

    }

    private void getDetailsMovie(int id) {

        final ProgressDialog dialog = ProgressDialog.show(DetailsActivity.this, "",
                "Carregando...", true);


        TMDbMovieService serviceMovie = Global.retrofitMovie.create(TMDbMovieService.class);

        Call<Movie> requestMovie = serviceMovie.show(id);

        requestMovie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(!response.isSuccess()){

                }else{
                    movie = response.body();
                    setPageData();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                dialog.dismiss();
            }
        });


    }

    private void setPageData() {
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        TextView textViewDate = (TextView) findViewById(R.id.textViewDate);
        TextView textViewOverview = (TextView) findViewById(R.id.textViewOverview);


        textViewName.setText(movie.title);
        textViewDate.setText("Lançamento em "+movie.release_date);
        textViewOverview.setText(movie.overview);


        if(movie.backdrop_path != null) {
            new DownloadImageTask((ImageView) findViewById(R.id.imageViewDetails)).execute("https://image.tmdb.org/t/p/original/" + movie.backdrop_path.substring(1));
        }else{
            new DownloadImageTask((ImageView) findViewById(R.id.imageViewDetails)).execute("https://image.tmdb.org/t/p/original/" + movie.poster_path.substring(1));
        }



    }


}
