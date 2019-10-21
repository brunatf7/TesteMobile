package com.labtecs.bruna.testemobile.Adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.labtecs.bruna.testemobile.Objects.Movie;
import com.labtecs.bruna.testemobile.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private static MovieClickListener movieClickListener;

    Resources resources;
    public List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void setOnItemClickListener(MovieClickListener movieClickListener) {
        MovieAdapter.movieClickListener = movieClickListener;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(final ViewGroup view, final int i) {
        final View rootView = LayoutInflater.from(view.getContext()).inflate(R.layout.card_movie, view, false);
        resources = view.getResources();
        return new MovieViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {

        Movie movie = movies.get(i);

        movieViewHolder.textViewName.setText(movie.title);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public interface MovieClickListener {
        void onItemClick(int position, View view);

    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName;

        MovieViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.txt_movie_name);

        }

        @Override
        public void onClick(View view) {
//            if (view.getId() == ) {
//                movieClickListener.onItemClick(getLayoutPosition(), view);
//            }
        }
    }
}
