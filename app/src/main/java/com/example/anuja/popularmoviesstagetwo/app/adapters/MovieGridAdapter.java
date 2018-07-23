package com.example.anuja.popularmoviesstagetwo.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuja.popularmoviesstagetwo.R;
import com.example.anuja.popularmoviesstagetwo.data.entity.MoviesEntity;
import com.example.anuja.popularmoviesstagetwo.databinding.MoviesItemListBinding;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * This is an adapter class to display movies in the grid format
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MoviesViewHolder> {

    private List<MoviesEntity> movieList;
    private GridItemClickListener itemClickListener;
    private Context context;

    // listener interface
    public interface GridItemClickListener {
        void onGridItemClick(MoviesEntity movie);
    }

    public MovieGridAdapter(List<MoviesEntity> movies, GridItemClickListener listener) {
        this.movieList = movies;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new MoviesViewHolder(MoviesItemListBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        MoviesEntity movie = movieList.get(position);

        String imagePath = MovieUtils.BASE_IMG_URL + MovieUtils.THUMB_IMG_SIZE + movie.getPosterPath();

        Picasso.with(context)
                .load(imagePath)
                .fit()
                .placeholder(R.drawable.movie_poster_placeholder)
                .into(holder.itemListBinding.ivMovieImages);
        }

    @Override
    public int getItemCount() {
        if (movieList == null)
            return 0;
        return movieList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MoviesItemListBinding itemListBinding;

        public MoviesViewHolder(MoviesItemListBinding itemListBinding) {
            super(itemListBinding.getRoot());
            this.itemListBinding = itemListBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            MoviesEntity movieDetail = movieList.get(position);
            itemClickListener.onGridItemClick(movieDetail);
        }
    }

    public void swapLists(List<MoviesEntity> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }
}
