package com.fyfirman.moviecatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailMovieActivity extends AppCompatActivity {
  public static final String EXTRA_MOVIE = "default_extra";
  private ImageView imgPhoto;
  private TextView txtTitle;
  private TextView txtSynopsis;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_movie);

    Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
    bindView(movie);
  }

  private void bindView(Movie movie){
    imgPhoto = findViewById(R.id.img_photo);
    txtTitle = findViewById(R.id.movie_title);
    txtSynopsis = findViewById(R.id.movie_synopsis);

    imgPhoto.setImageResource(movie.getPhoto());
    txtTitle.setText(movie.getTitle());
    txtSynopsis.setText(movie.getSynopsis());
  }
}
