package com.fyfirman.moviecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.fyfirman.moviecatalogue.data.Movie;
import com.fyfirman.moviecatalogue.R;
import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

  private Context context;
  private ArrayList<Movie> movies;

  public MovieAdapter(Context context) {
    this.context = context;
    movies = new ArrayList<>();
  }

  public void setMovies(ArrayList<Movie> movies) {
    this.movies = movies;
  }

  @Override
  public int getCount() {
    return movies.size();
  }

  @Override
  public Object getItem(int i) {
    return movies.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    if (view == null) {
      view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
    }

    MovieItemViewHolder viewHolder = new MovieItemViewHolder(view);
    Movie movie = (Movie) getItem(i);
    viewHolder.bind(movie);
    return view;
  }

  private class MovieItemViewHolder {
    private ImageView imgPhoto;
    private TextView txtTitle;
    private TextView txtSynopsis;

    public MovieItemViewHolder(View view) {
      this.imgPhoto = view.findViewById(R.id.img_photo);
      this.txtTitle = view.findViewById(R.id.movie_title);
      this.txtSynopsis = view.findViewById(R.id.movie_synopsis);
    }

    void bind(Movie movie){
      imgPhoto.setImageResource(movie.getPhoto());
      txtTitle.setText(movie.getTitle());
      txtSynopsis.setText(movie.getSynopsis());
    }
  }
}
