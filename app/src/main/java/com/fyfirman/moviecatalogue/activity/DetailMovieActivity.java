package com.fyfirman.moviecatalogue.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.data.Movie;

public class DetailMovieActivity extends AppCompatActivity {

  public static final String EXTRA_MOVIE = "default_extra";
  private ImageView imgPhoto;
  private TextView txtTitle;
  private TextView txtOverview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_movie);

    Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
    bindView(movie);
  }

  private void bindView(Movie movie) {
    imgPhoto = findViewById(R.id.img_photo);
    txtTitle = findViewById(R.id.movie_title);
    txtOverview = findViewById(R.id.movie_synopsis);

    txtTitle.setText(movie.getTitle());
    txtOverview.setText(movie.getOverview());
    Glide.with(getApplicationContext())
        .load(movie.getPoster_path())
        .into(imgPhoto);
  }
}
