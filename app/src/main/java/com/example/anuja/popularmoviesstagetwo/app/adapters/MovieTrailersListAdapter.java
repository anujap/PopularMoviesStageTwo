package com.example.anuja.popularmoviesstagetwo.app.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuja.popularmoviesstagetwo.R;
import com.example.anuja.popularmoviesstagetwo.databinding.TrailerItemListBinding;
import com.example.anuja.popularmoviesstagetwo.model.TrailerResults;
import com.example.anuja.popularmoviesstagetwo.model.youtubemodel.YoutubePage;
import com.example.anuja.popularmoviesstagetwo.webservice.MovieUtils;
import com.example.anuja.popularmoviesstagetwo.webservice.YoutubeRetrofitClient;
import com.example.anuja.popularmoviesstagetwo.webservice.YoutubeWebserviceInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This is an adapter class to display the movie trailers
 *
 * NOTE:- this class fetches the key from the trailers object and passes it
 * to the youtube api in order to fetch the trailers url to display it
 * as thumbnails
 */
public class MovieTrailersListAdapter extends RecyclerView.Adapter<MovieTrailersListAdapter.TrailersListViewHolder> {

    private List<TrailerResults> trailerResultsList;
    private ListItemClickListener listItemClickListener;
    private YoutubeWebserviceInterface webserviceInterface = YoutubeRetrofitClient.getInstance().getMovieWebservice();
    private Context context;

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
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new TrailersListViewHolder(TrailerItemListBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersListViewHolder holder, int position) {
        TrailerResults trailerResult = trailerResultsList.get(position);

        if(TextUtils.equals(trailerResult.getSite(), MovieUtils.TRAILER_SITE_YOUTUBE)) {
            webserviceInterface.getYoutubeMovieTrailers(trailerResult.getKey()).enqueue(new Callback<YoutubePage>() {
                @Override
                public void onResponse(Call<YoutubePage> call, Response<YoutubePage> response) {
                    String url = response.body().getItems().get(0).getSnippet().getThumbnails().getMedium().getUrl();
                    Picasso.with(context)
                                .load(url)
                                .fit()
                                .placeholder(R.drawable.movie_poster_placeholder)
                                .into(holder.itemListBinding.ivTrailerItem);
                }

                @Override
                public void onFailure(Call<YoutubePage> call, Throwable t) {
                }
            });
        }

        holder.itemListBinding.tvTrailerItem.setText(trailerResult.getName());
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
