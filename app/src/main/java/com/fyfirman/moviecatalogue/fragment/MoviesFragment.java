package com.fyfirman.moviecatalogue.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.activity.DetailMovieActivity;
import com.fyfirman.moviecatalogue.adapter.MovieAdapter;
import com.fyfirman.moviecatalogue.data.Movie;
import com.fyfirman.moviecatalogue.viewmodel.MovieViewModel;
import java.util.ArrayList;

public class MoviesFragment extends Fragment {

  private RecyclerView rvMovie;
  private ArrayList<Movie> listMovie = new ArrayList<>();
  private MovieViewModel movieViewModel;
  private MovieAdapter movieAdapter;
  private ProgressBar progressBar;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movies, container, false);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    progressBar = getView().findViewById(R.id.indeterminateBar);
    showLoading(true);

    showRecyclerList(view);

  }

  private void showRecyclerList(View view) {
    //mencari referensi recycle view
    rvMovie = view.findViewById(R.id.rv_movie);
    rvMovie.setHasFixedSize(true);

    //set layout manager
    rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));

    //set adapter
    movieAdapter = new MovieAdapter(getContext(), listMovie);
    rvMovie.setAdapter(movieAdapter);

    //buat onclick callback function
    movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
      @Override
      public void onItemClicked(Movie data) {
        showSelectedMovie(data);
      }
    });

    movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
        .get(MovieViewModel.class);

    movieViewModel.setData(getResources().getString(R.string.language));

//    Observe
    movieViewModel.getData().observe(this, new Observer<ArrayList<Movie>>() {
      @Override
      public void onChanged(ArrayList<Movie> mModels) {
        if (mModels != null) {
          movieAdapter.setData(mModels);
          showLoading(false);
        }
      }
    });
  }

  private void showSelectedMovie(Movie data) {
//    Toast toast = Toast.makeText(getActivity(),"Hello TV Show!",Toast.LENGTH_SHORT);
//    toast.show();

    Intent showDetailMovie = new Intent(getActivity(), DetailMovieActivity.class);

    showDetailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE, data);
    startActivity(showDetailMovie);
  }

  private void showLoading(Boolean state) {
    if (state) {
      progressBar.setVisibility(View.VISIBLE);
    } else {
      progressBar.setVisibility(View.GONE);
    }
  }
}