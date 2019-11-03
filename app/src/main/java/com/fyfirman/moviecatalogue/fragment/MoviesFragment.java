package com.fyfirman.moviecatalogue.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.activity.DetailMovieActivity;
import com.fyfirman.moviecatalogue.adapter.MovieAdapter;
import com.fyfirman.moviecatalogue.data.Movie;
import java.util.ArrayList;

public class MoviesFragment extends Fragment {
  private RecyclerView rvMovie;
  private ArrayList<Movie> listMovie = new ArrayList<>();

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movies, container, false);
    showRecyclerList(view);

    return view;
  }

  public ArrayList<Movie> getListMovie(){
    String[] dataTitle = getResources().getStringArray(R.array.tv_show_title);
    String[] dataSynopsis = getResources().getStringArray(R.array.tv_show_synopsis);
    TypedArray dataPhoto = getResources().obtainTypedArray(R.array.tv_show_photo);

    ArrayList<Movie> listMovie = new ArrayList<>();
    for (int i = 0; i < dataTitle.length; i++) {
      Movie movie = new Movie();
      movie.setTitle(dataTitle[i]);
      movie.setSynopsis(dataSynopsis[i]);
      movie.setPhoto(dataPhoto.getResourceId(i,1));
      listMovie.add(movie);
    }
    return listMovie;
  }

  private void showRecyclerList(View view){
    //mencari referensi recycle view
    rvMovie = view.findViewById(R.id.rv_movie);
    rvMovie.setHasFixedSize(true);

    //mengambil data
    listMovie.addAll(getListMovie());

    //set layout manager
    rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));

    //set adapter
    MovieAdapter movieAdapter = new MovieAdapter(listMovie);
    rvMovie.setAdapter(movieAdapter);

    //buat onclick callback function
    movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
      @Override
      public void onItemClicked(Movie data) {
        showSelectedMovie(data);
      }
    });
  }

  private void showSelectedMovie(Movie data){
//    Toast toast = Toast.makeText(getActivity(),"Hello TV Show!",Toast.LENGTH_SHORT);
//    toast.show();

    Intent showDetailMovie = new Intent(getActivity(), DetailMovieActivity.class);

    showDetailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE, data);
    startActivity(showDetailMovie);
  }
}