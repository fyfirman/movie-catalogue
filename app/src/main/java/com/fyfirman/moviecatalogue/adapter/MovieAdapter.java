package com.fyfirman.moviecatalogue.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.data.Movie;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {

  private ArrayList<Movie> listMovie;
  private Context context;

  public MovieAdapter(Context context, ArrayList<Movie> list) {
    this.context = context;
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

  public void setData(ArrayList<Movie> items) {
    listMovie.clear();
    listMovie.addAll(items);
    notifyDataSetChanged();
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

    listViewHolder.tvTitle.setText(movie.getTitle());
    listViewHolder.tvSynopsis.setText(movie.getOverview());
    Glide.with(context)
        .load(movie.getPoster_path())
        .into(listViewHolder.imgPhoto);

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
