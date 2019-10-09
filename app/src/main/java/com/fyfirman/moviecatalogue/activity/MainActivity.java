package com.fyfirman.moviecatalogue.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.fyfirman.moviecatalogue.adapter.MainFragmentPagerAdapter;
import com.fyfirman.moviecatalogue.fragment.MoviesFragment;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabLayout();


    }

    private void initTabLayout(){
        // setting toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setting view pager
        ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        // setting tabLayout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mainFragmentPagerAdapter.addFragment(new MoviesFragment(), getString(R.string.movies));
        mainFragmentPagerAdapter.addFragment(new TvShowFragment(), getString(R.string.tv_show));
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }


}
