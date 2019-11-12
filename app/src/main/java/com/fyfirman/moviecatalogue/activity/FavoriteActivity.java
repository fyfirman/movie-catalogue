package com.fyfirman.moviecatalogue.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.adapter.MainFragmentPagerAdapter;
import com.fyfirman.moviecatalogue.fragment.MoviesFragment;
import com.fyfirman.moviecatalogue.fragment.TvShowFragment;

public class FavoriteActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_favorite);

  }
  private void initTabLayout() {
    // setting toolbar
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // setting view pager
    ViewPager viewPager = findViewById(R.id.view_pager_fav);
    setupViewPager(viewPager);

    // setting tabLayout
    TabLayout tabLayout = findViewById(R.id.tab_layout_favorite);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupViewPager(ViewPager viewPager) {
//    TODO: Buat pageradapter (kayanya ga perlu deng)
    MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(
        getSupportFragmentManager());
//    TODO: Ubah sesuai fragment yg dituju, tambahin string resourcenya
    mainFragmentPagerAdapter.addFragment(new MoviesFragment(), getString(R.string.movies));
    mainFragmentPagerAdapter.addFragment(new TvShowFragment(), getString(R.string.tv_show));
    viewPager.setAdapter(mainFragmentPagerAdapter);
  }
  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }
}
