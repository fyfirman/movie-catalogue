package com.fyfirman.moviecatalogue;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataTitle;
    private String[] dataSynopsis;
    private TypedArray dataPhoto;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabLayout();

        movieAdapter = new MovieAdapter(this);
//        ListView listView = findViewById(R.id.movie_list);
//        listView.setAdapter(movieAdapter);
//
//        prepare();
//        addItem();
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent showDetailMovie = new Intent(MainActivity.this, DetailMovieActivity.class);
//                showDetailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies.get(i));
//                startActivity(showDetailMovie);
//            }
//        });

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

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataSynopsis = getResources().getStringArray(R.array.data_synopsis);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem(){
        movies = new ArrayList<Movie>();

        for (int i = 0; i < dataTitle.length; i++){
            Movie movie = new Movie();
            movie.setPhoto(dataPhoto.getResourceId(i,1));
            movie.setTitle(dataTitle[i]);
            movie.setSynopsis(dataSynopsis[i]);
            movies.add(movie);
        }
        movieAdapter.setMovies(movies);
    }
}
