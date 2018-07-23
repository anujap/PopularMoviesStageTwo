package com.example.anuja.popularmoviesstagetwo.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuja.popularmoviesstagetwo.databinding.ReviewItemListBinding;
import com.example.anuja.popularmoviesstagetwo.model.ReviewResults;
import com.example.anuja.popularmoviesstagetwo.app.adapters.MovieTrailersListAdapter.ListItemClickListener;

import java.util.List;

/**
 * This is an adapter class to display the movie reviews
 */
public class MovieReviewsListAdapter extends RecyclerView.Adapter<MovieReviewsListAdapter.ReviewsListViewHolder> {

    private List<ReviewResults> reviewResultsList;
    private ListItemClickListener listItemClickListener;

    public MovieReviewsListAdapter(List<ReviewResults> reviewResultsList, ListItemClickListener listItemClickListener) {
        this.reviewResultsList = reviewResultsList;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public ReviewsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ReviewsListViewHolder(ReviewItemListBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsListViewHolder holder, int position) {
        ReviewResults reviewResults = reviewResultsList.get(position);
        holder.itemListBinding.tvReviewAuthor.setText(reviewResults.getAuthor());
        holder.itemListBinding.tvReviewContent.setText(reviewResults.getContent());
    }

    @Override
    public int getItemCount() {
        if(reviewResultsList == null)
            return 0;
        return reviewResultsList.size();
    }

    public void swapLists(List<ReviewResults> reviewResultsList) {
        this.reviewResultsList = reviewResultsList;
        notifyDataSetChanged();
    }

    public class ReviewsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ReviewItemListBinding itemListBinding;

        public ReviewsListViewHolder(ReviewItemListBinding itemListBinding) {
            super(itemListBinding.getRoot());

            this.itemListBinding = itemListBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ReviewResults reviewResult = reviewResultsList.get(position);
            listItemClickListener.onListItemClick(reviewResult.getUrl());
        }
    }
}