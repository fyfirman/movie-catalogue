package com.fyfirman.moviecatalogue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.fragment.ExploreFragment;
import com.fyfirman.moviecatalogue.fragment.FavoriteFragment;
import com.fyfirman.moviecatalogue.fragment.MoviesFavoriteFragment;
import com.fyfirman.moviecatalogue.fragment.MoviesFragment;
import com.fyfirman.moviecatalogue.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    loadFragment(new MoviesFragment());

    initBottomNavigation();
  }

  private void initBottomNavigation() {
    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
    bottomNavigationView.setOnNavigationItemSelectedListener(this);
  }

  private boolean loadFragment(Fragment fragment) {
    if (fragment != null) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.fl_container, fragment)
          .commit();
      return true;
    }
    return false;
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    Fragment fragment = null;
    switch (item.getItemId()) {
      case R.id.movies_menu:
        fragment = new MoviesFragment();
        break;
      case R.id.tv_show_menu:
        fragment = new TvShowFragment();
        break;
      case R.id.favorite_menu:
        fragment = new FavoriteFragment();
        break;
    }
    return loadFragment(fragment);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_change_settings) {
      Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
      startActivity(mIntent);
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onPointerCaptureChanged(boolean hasCapture) {

  }
}
