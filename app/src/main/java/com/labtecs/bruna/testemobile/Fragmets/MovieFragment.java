package com.labtecs.bruna.testemobile.Fragmets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.labtecs.bruna.testemobile.Activitys.DetailsActivity;
import com.labtecs.bruna.testemobile.Adapters.MovieAdapter;
import com.labtecs.bruna.testemobile.Global;
import com.labtecs.bruna.testemobile.Objects.Popular;
import com.labtecs.bruna.testemobile.R;
import com.labtecs.bruna.testemobile.Services.TMDbMovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {

    public RecyclerView recyclerView;
    public Popular popular;

    public MovieFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movie, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        setReclerViewLayout("List");
        getPopularMovies();



        return rootView;
    }

    private void getPopularMovies() {
        TMDbMovieService service = Global.retrofitMovie.create(TMDbMovieService.class);

        Call<Popular> requestPopular = service.list();

        requestPopular.enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                if(!response.isSuccess()){

                }else{
                    popular = response.body();
                    setAdapterData();
                }
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {

            }
        });
    }

    private void setAdapterData() {
        final MovieAdapter adapter = new MovieAdapter(popular.results);
        adapter.setOnItemClickListener(onClickItem);
        recyclerView.setAdapter(adapter);
    }

    public MovieAdapter.MovieClickListener onClickItem = new MovieAdapter.MovieClickListener() {
        @Override
        public void onItemClick(int position, View view) {
            Intent intent = new Intent(getActivity(), DetailsActivity.class);

            intent.putExtra("id", popular.results.get(position).id);

            startActivity(intent);
        }
    };

    public void setReclerViewLayout(String layout) {

        RecyclerView.LayoutManager layoutManager;

        switch (layout){
            case "List":
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                break;
            case "Grid":
                layoutManager = new GridLayoutManager(getContext(),2);
                recyclerView.setLayoutManager(layoutManager);
                break;
        }
    }


}
