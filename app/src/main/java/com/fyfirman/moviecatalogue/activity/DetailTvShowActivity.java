package com.fyfirman.moviecatalogue.activity;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.adapter.ListTvShowAdapter;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import com.fyfirman.moviecatalogue.database.TvShowFavoriteHelper;

public class DetailTvShowActivity extends AppCompatActivity {

  public static final String EXTRA_TV_SHOW = "default_extra";
  private ImageView imgPhoto;
  private TextView txtTitle;
  private TextView txtOverview;
  private ProgressBar progressBar;
  private TextView txtOverviewHeader;
  private Boolean isFavorite = false ;
  private ListTvShowAdapter listTvShowAdapter = new ListTvShowAdapter(this);
  private TvShowFavoriteHelper tvShowFavoriteHelper;
  private Menu menuItem = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_tv_show);

    imgPhoto = findViewById(R.id.img_photo);
    txtTitle = findViewById(R.id.tv_show_title);
    txtOverview = findViewById(R.id.tv_show_synopsis);
    progressBar = findViewById(R.id.indeterminateBar);
    txtOverviewHeader = findViewById(R.id.txtOverView);
    tvShowFavoriteHelper = TvShowFavoriteHelper.getInstance(getApplicationContext());

    new LoadTvShowData().execute();

    getFavoriteState();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.detail_menu, menu);
    menuItem = menu;
    setFavorite();
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.add_to_favorite) {
      if (isFavorite) {
        removeFromFavorite();
      } else {
        addToFavorite();
      }
      isFavorite = !isFavorite;
      setFavorite();
    }
    return super.onOptionsItemSelected(item);
  }
  public void getFavoriteState() {
    Tv_Show tv_show = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
    listTvShowAdapter.setData(tvShowFavoriteHelper.selectTvShow(tv_show.getId().toString()));
    listTvShowAdapter.notifyDataSetChanged();

    if (listTvShowAdapter.getItemCount() > 0) {
      isFavorite = true;
    }
  }

  public void setFavorite() {
    if (isFavorite) {
      menuItem.getItem(0)
          .setIcon(ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites));
    } else {
      menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites));
    }
  }

  private void addToFavorite() {
    try {
      Tv_Show tv_show = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
      tvShowFavoriteHelper.insertTvShow(tv_show);
      Toast.makeText(this, getResources().getString(R.string.add_fav_success), Toast.LENGTH_SHORT)
          .show();
    } catch (SQLiteConstraintException e) {
      Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
  }

  private void removeFromFavorite() {
    try {
      Tv_Show tv_show = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
      tvShowFavoriteHelper.deleteTvShow(tv_show.getId().toString());
      Toast.makeText(this, getResources().getString(R.string.del_fav_success), Toast.LENGTH_SHORT)
          .show();
    } catch (SQLiteConstraintException e) {
      Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
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

  public class LoadTvShowData extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      showLoading(true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
      try {
        Thread.sleep(1000);
      } catch (Exception ignored) {
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void voids) {
      super.onPostExecute(voids);
      showLoading(false);

      Tv_Show tv_show = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
      bindView(tv_show);
    }

    private void bindView(Tv_Show tv_show) {
      txtTitle.setText(tv_show.getTitle());
      txtOverview.setText(tv_show.getOverview());
      Glide.with(getApplicationContext())
          .load(tv_show.getPoster_path())
          .into(imgPhoto);
    }
  }

}
