package com.fyfirman.moviecatalogue.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.data.Movie;

public class DetailMovieActivity extends AppCompatActivity {

  public static final String EXTRA_MOVIE = "default_extra";
  private ImageView imgPhoto;
  private TextView txtTitle;
  private TextView txtOverview;
  private ProgressBar progressBar;
  private TextView txtOverviewHeader;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_movie);

    imgPhoto = findViewById(R.id.img_photo);
    txtTitle = findViewById(R.id.movie_title);
    txtOverview = findViewById(R.id.movie_synopsis);
    progressBar = findViewById(R.id.indeterminateBar);
    txtOverviewHeader = findViewById(R.id.txtOverView);

    new LoadMovieData().execute();
  }

  private void showLoading(Boolean state) {
    if (state) {
      progressBar.setVisibility(View.VISIBLE);
      imgPhoto.setVisibility(View.GONE);
      txtTitle.setVisibility(View.GONE);
      txtOverview.setVisibility(View.GONE);
      txtOverviewHeader.setVisibility(View.GONE);
    } else {
      progressBar.setVisibility(View.GONE);
      imgPhoto.setVisibility(View.VISIBLE);
      txtTitle.setVisibility(View.VISIBLE);
      txtOverview.setVisibility(View.VISIBLE);
      txtOverviewHeader.setVisibility(View.VISIBLE);
    }
  }

  public class LoadMovieData extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      showLoading(true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void voids) {
      super.onPostExecute(voids);
      showLoading(false);

      Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
      bindView(movie);
    }

    private void bindView(Movie movie) {
      txtTitle.setText(movie.getTitle());
      txtOverview.setText(movie.getOverview());
      Glide.with(getApplicationContext())
          .load(movie.getPoster_path())
          .into(imgPhoto);
    }
  }

}
