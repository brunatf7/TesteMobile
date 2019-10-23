package com.labtecs.bruna.testemobile.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.labtecs.bruna.testemobile.Adapters.MovieAdapter;
import com.labtecs.bruna.testemobile.Global;
import com.labtecs.bruna.testemobile.Objects.Popular;
import com.labtecs.bruna.testemobile.R;
import com.labtecs.bruna.testemobile.Services.TMDbSearchService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public Popular popular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String query = getIntent().getStringExtra("query");

        toolbar.setTitle("Procurando por '"+query+"'");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getSearchMovies(query);

    }

    private void getSearchMovies(String query) {

        final ProgressDialog dialog = ProgressDialog.show(SearchActivity.this, "",
                "Carregando...", true);

        TMDbSearchService serviceSearch = Global.retrofitSearch.create(TMDbSearchService.class);

        Call<Popular> requestPopular = serviceSearch.search(query);

        requestPopular.enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                if(!response.isSuccess()){

                }else{

                    popular = response.body();
                    setAdapterData();

                    Log.d("RequestSearch", popular.toString());

                }

                Log.d("RequestSearch", response.raw().toString());

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {
                dialog.dismiss();
            }
        });

    }

    private void setAdapterData() {
        final MovieAdapter adapter = new MovieAdapter(popular.results);
        adapter.setOnItemClickListener(new MovieAdapter.MovieClickListener() {
            @Override
            public void onItemClick(int position, View view) {

                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);

                intent.putExtra("id", popular.results.get(position).id.intValue());

                startActivity(intent);


            }
        });
        recyclerView.setAdapter(adapter);
    }

}
