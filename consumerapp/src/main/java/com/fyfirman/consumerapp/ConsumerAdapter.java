package com.fyfirman.consumerapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ConsumerAdapter extends RecyclerView.Adapter<ConsumerAdapter.FavoriteMovieViewHolder> {

    private final ArrayList<FavoriteMovieItem> listFavoriteMovie = new ArrayList<>();
    private final Activity activity;

    public ConsumerAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<FavoriteMovieItem> getListFavoriteMovie() {
        return listFavoriteMovie;
    }

    public void setListFavoriteMovie(ArrayList<FavoriteMovieItem> listFavoriteMovie) {
        this.listFavoriteMovie.clear();
        this.listFavoriteMovie.addAll(listFavoriteMovie);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieViewHolder holder, int i) {
        holder.tvTitle.setText(getListFavoriteMovie().get(i).getTitle());
        holder.tvSynopsis.setText(getListFavoriteMovie().get(i).getOverview());
        Glide.with(holder.itemView.getContext())
                .load(getListFavoriteMovie().get(i).getPosterPath())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return listFavoriteMovie.size();
    }

    public class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvSynopsis;
        ImageView ivPoster;

        public FavoriteMovieViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.movie_title);
            tvSynopsis = itemView.findViewById(R.id.movie_synopsis);
            ivPoster = itemView.findViewById(R.id.img_photo);
        }
    }
}
