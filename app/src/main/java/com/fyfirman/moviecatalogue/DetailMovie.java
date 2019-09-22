package com.fyfirman.moviecatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailMovie extends AppCompatActivity {
  public static final String EXTRA_MOVIE = "default_extra";

  TextView txtTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_movie);

    txtTitle = findViewById(R.id.movie_title);
    Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

  }

  private void bindView(Movie movie){
    txtTitle.setText(movie.getTitle());
  }

}
