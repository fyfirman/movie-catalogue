package com.fyfirman.moviecatalogue.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.fyfirman.moviecatalogue.data.Movie;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.activity.DetailMovieActivity;
import com.fyfirman.moviecatalogue.adapter.MovieAdapter;
import java.util.ArrayList;

public class MoviesFragment extends Fragment {
  private String[] dataTitle;
  private String[] dataSynopsis;
  private TypedArray dataPhoto;
  private MovieAdapter movieAdapter;
  private ArrayList<Movie> movies;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movies, container, false);

    movieAdapter = new MovieAdapter(getActivity());

    ListView listView = view.findViewById(R.id.movie_list);
    listView.setAdapter(movieAdapter);

    getResourceFromView();
    addItem();

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast toast = Toast.makeText(getActivity(),"Hello Movies!",Toast.LENGTH_SHORT);
//        toast.show();
          Intent showDetailMovie = new Intent(getActivity(), DetailMovieActivity.class);
          showDetailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies.get(i));
          startActivity(showDetailMovie);
      }
    });

    return view;
  }


  private void getResourceFromView() {
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