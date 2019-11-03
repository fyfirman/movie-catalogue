package com.fyfirman.moviecatalogue.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.data.Movie;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder>  {
  private ArrayList<Movie> listMovie;

  public MovieAdapter(ArrayList<Movie> list) {
    this.listMovie = list;
  }

  public class ListViewHolder extends ViewHolder {
    ImageView imgPhoto;
    TextView tvTitle, tvSynopsis;

    ListViewHolder(View itemView) {
      super(itemView);
      imgPhoto = itemView.findViewById(R.id.tv_img_photo);
      tvTitle = itemView.findViewById(R.id.tv_show_title);
      tvSynopsis = itemView.findViewById(R.id.tv_show_synopsis);
    }
  }

  @NonNull
  @Override
  public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
    View view = layoutInflater.inflate(R.layout.item_row_tv_show, viewGroup, false);
    return new ListViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, int i) {
    Movie movie = listMovie.get(i);

    listViewHolder.imgPhoto.setImageResource(movie.getPhoto());
    listViewHolder.tvTitle.setText(movie.getTitle());
    listViewHolder.tvSynopsis.setText(movie.getSynopsis());

    listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onItemClickCallback.onItemClicked(listMovie.get(listViewHolder.getAdapterPosition()));
      }
    });
  }

  @Override
  public int getItemCount() {
    return listMovie.size();
  }

  private OnItemClickCallback onItemClickCallback;

  public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
    this.onItemClickCallback = onItemClickCallback;
  }

  public interface OnItemClickCallback {
    void onItemClicked(Movie data);
  }
}
