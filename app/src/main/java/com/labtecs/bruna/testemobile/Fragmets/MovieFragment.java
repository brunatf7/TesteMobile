package com.labtecs.bruna.testemobile.Fragmets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labtecs.bruna.testemobile.Activitys.DetailsActivity;
import com.labtecs.bruna.testemobile.Adapters.MovieAdapter;
import com.labtecs.bruna.testemobile.Global;
import com.labtecs.bruna.testemobile.Objects.Popular;
import com.labtecs.bruna.testemobile.R;
import com.labtecs.bruna.testemobile.Services.TMDbMovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    public RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManagerList, layoutManagerGrid;
    private String layout = "List";

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
        layoutManagerList = new LinearLayoutManager(getContext());
        layoutManagerGrid = new GridLayoutManager(getContext(),2);
        setRecyclerViewLayout();
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
        adapter.setOnItemClickListener(new MovieAdapter.MovieClickListener() {
            @Override
            public void onItemClick(int position, View view) {

                Intent intent = new Intent(getContext(), DetailsActivity.class);

                intent.putExtra("id", popular.results.get(position).id.intValue());

                startActivity(intent);


            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setRecyclerViewLayout() {

        switch (layout){
            case "List":
                recyclerView.setLayoutManager(layoutManagerList);
                break;
            case "Grid":
                recyclerView.setLayoutManager(layoutManagerGrid);
                break;
        }
        if(popular != null){
            ((MovieAdapter) recyclerView.getAdapter()).changeAdapterViewLayout();
        }


    }


    public String getRecyclerViewLayout(){
        return layout;
    }

    public void changeRecyclerViewLayout(){
        if(layout=="List"){
            layout = "Grid";
        }else{
            layout = "List";
        }

        setRecyclerViewLayout();
    }


}
