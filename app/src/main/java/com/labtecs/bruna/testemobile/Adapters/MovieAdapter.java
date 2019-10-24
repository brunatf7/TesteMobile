package com.labtecs.bruna.testemobile.Adapters;

import android.content.res.Resources;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.labtecs.bruna.testemobile.Objects.Movie;
import com.labtecs.bruna.testemobile.R;
import com.labtecs.bruna.testemobile.Services.DownloadImageTask;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static MovieClickListener movieClickListener;

    Resources resources;
    public List<Movie> movies;
    private String layout = "List";

    private static final int TYPE_LIST = 1;
    private static final int TYPE_GRID = 2;

    public MovieAdapter(List<Movie> movies) {

        this.movies = movies;
    }

    public void changeAdapterViewLayout(){
        if(layout=="List"){
            layout = "Grid";
        }else{
            layout = "List";
        }
    }

    public void setOnItemClickListener(MovieClickListener movieClickListener) {
        MovieAdapter.movieClickListener = movieClickListener;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (this.layout == "List"){
            return TYPE_LIST;
        }else if (this.layout == "Grid"){
            return TYPE_GRID;
        }

        return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {


        View rootView = null;

        resources = parent.getResources();

        if (viewType == TYPE_LIST) {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_list, parent, false);
            return new MovieViewHolderList(rootView);
        } else if (viewType == TYPE_GRID) {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie_grid, parent, false);
            return new MovieViewHolderGrid(rootView);
        }else{
            throw new RuntimeException("The type has to be List or Grid");
        }


    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        switch (holder.getItemViewType()) {
            case TYPE_LIST:
                initLayoutList((MovieViewHolderList) holder, i);
                break;
            case TYPE_GRID:
                initLayoutGrid((MovieViewHolderGrid) holder, i);
                break;
            default:
                break;
        }



    }

    private void initLayoutList(MovieViewHolderList holder, int i) {
        Movie movie = movies.get(i);
        holder.textViewName.setText(movie.title);
    }

    private void initLayoutGrid(MovieViewHolderGrid holder, int i) {
        Movie movie = movies.get(i);
        holder.textViewName.setText(movie.title);

        if(movie.backdrop_path != null) {
            new DownloadImageTask(holder.imageViewMovie).execute("https://image.tmdb.org/t/p/original/" + movie.backdrop_path.substring(1));
        }else{
            new DownloadImageTask(holder.imageViewMovie).execute("https://image.tmdb.org/t/p/original/" + movie.poster_path.substring(1));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public interface MovieClickListener {
        void onItemClick(int position, View view);

    }

    public static class MovieViewHolderList extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout cardView;
        TextView textViewName;

        MovieViewHolderList(View itemView) {
            super(itemView);

            cardView = (LinearLayout) itemView.findViewById(R.id.linear_view_movie);
            textViewName = (TextView) itemView.findViewById(R.id.txt_movie_name);


            cardView.setOnClickListener(this);
            textViewName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            movieClickListener.onItemClick(getLayoutPosition(), view);

        }
    }

    public static class MovieViewHolderGrid extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout cardView;
        TextView textViewName;
        ImageView imageViewMovie;

        MovieViewHolderGrid(View itemView) {
            super(itemView);

            cardView = (LinearLayout) itemView.findViewById(R.id.linear_view_movie);
            textViewName = (TextView) itemView.findViewById(R.id.txt_movie_name);
            imageViewMovie = (ImageView) itemView.findViewById(R.id.image_movie);

            cardView.setOnClickListener(this);
            textViewName.setOnClickListener(this);
            imageViewMovie.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            movieClickListener.onItemClick(getLayoutPosition(), view);

        }
    }
}
