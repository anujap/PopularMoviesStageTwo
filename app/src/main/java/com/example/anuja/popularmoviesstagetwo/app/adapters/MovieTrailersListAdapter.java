package com.example.anuja.popularmoviesstagetwo.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuja.popularmoviesstagetwo.databinding.TrailerItemListBinding;
import com.example.anuja.popularmoviesstagetwo.model.TrailerResults;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieUtils;

import java.util.List;

public class MovieTrailersListAdapter extends RecyclerView.Adapter<MovieTrailersListAdapter.TrailersListViewHolder> {

    private List<TrailerResults> trailerResultsList;
    private ListItemClickListener listItemClickListener;

    public interface ListItemClickListener {
        void onListItemClick(String url);
    }

    public MovieTrailersListAdapter(List<TrailerResults> trailerResultsList, ListItemClickListener listItemClickListener) {
        this.trailerResultsList = trailerResultsList;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public TrailersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new TrailersListViewHolder(TrailerItemListBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersListViewHolder holder, int position) {
        TrailerResults trailerResult = trailerResultsList.get(position);

        if(TextUtils.equals(trailerResult.getSite(), MovieUtils.TRAILER_SITE_YOUTUBE)) {

        }

        holder.itemListBinding.tvTrailerItem.setText(trailerResult.getName());
        //youtube
    }

    @Override
    public int getItemCount() {
        if(trailerResultsList == null)
            return 0;

        return trailerResultsList.size();
    }

    public void swapLists(List<TrailerResults> trailerResultsList) {
        this.trailerResultsList = trailerResultsList;
        notifyDataSetChanged();
    }

    public class TrailersListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TrailerItemListBinding itemListBinding;

        public TrailersListViewHolder(TrailerItemListBinding itemListBinding) {
            super(itemListBinding.getRoot());

            this.itemListBinding = itemListBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TrailerResults trailerResult = trailerResultsList.get(position);
            String youtube_url = MovieUtils.YOUTUBE_URL_INTENT + trailerResult.getKey();
            listItemClickListener.onListItemClick(youtube_url);
        }
    }
}
