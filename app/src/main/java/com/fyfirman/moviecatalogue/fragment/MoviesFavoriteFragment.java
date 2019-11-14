package com.fyfirman.moviecatalogue.fragment;

import static com.fyfirman.moviecatalogue.contentProvider.DatabaseContract.FavoriteMovieColumns.CONTENT_URI;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import com.fyfirman.moviecatalogue.contentProvider.LoadFavoriteMovieCallback;
import com.fyfirman.moviecatalogue.data.Movie;
import com.fyfirman.moviecatalogue.database.MovieFavoriteHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MoviesFavoriteFragment extends Fragment {

  private static final String EXTRA_STATE = "EXTRA_STATE";
  private RecyclerView rvFavMovie;
  private ArrayList<Movie> listMovie = new ArrayList<>();
  private MovieAdapter movieAdapter;
  private ProgressBar progressBar;
  private MovieFavoriteHelper movieFavoriteHelper;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_movies_favorite, container, false);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    progressBar = getView().findViewById(R.id.indeterminateBar);
    showLoading(true);

    showRecyclerList(view);

    movieFavoriteHelper = MovieFavoriteHelper.getInstance(getContext());

    if (savedInstanceState == null) {
      new LoadMovieFavoriteData().execute();
    } else {
      ArrayList<Movie> movie = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
      if (movie != null) {
        movieAdapter.setData(movie);
        movieAdapter.notifyDataSetChanged();
      }
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    new LoadMovieFavoriteData().execute();
  }

  private void showRecyclerList(View view) {
    //mencari referensi recycle view
    rvFavMovie = view.findViewById(R.id.rv_movie_favorite);
    rvFavMovie.setHasFixedSize(true);

    //set layout manager
    rvFavMovie.setLayoutManager(new LinearLayoutManager(getActivity()));

    //set adapter
    movieAdapter = new MovieAdapter(getContext(), listMovie);
    rvFavMovie.setAdapter(movieAdapter);

    //buat onclick callback function
    movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
      @Override
      public void onItemClicked(Movie data) {
        showSelectedMovie(data);
      }
    });
  }

  private void showSelectedMovie(Movie data) {
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

  public static class DataObserver extends ContentObserver {

    final Context context;

    public DataObserver(Handler handler, Context context) {
      super(handler);
      this.context = context;
    }

    @Override
    public void onChange(boolean selfChange) {
      super.onChange(selfChange);
      new LoadFavoriteMovieAsync(context, (LoadFavoriteMovieCallback) context).execute();
    }
  }

  private static class LoadFavoriteMovieAsync extends AsyncTask<Void, Void, Cursor> {

    private final WeakReference<Context> weakContext;

    private LoadFavoriteMovieAsync(Context context, LoadFavoriteMovieCallback callback) {
      weakContext = new WeakReference<>(context);
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
      Context context = weakContext.get();
      return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
    }

    @Override
    protected void onPostExecute(Cursor favoriteMovies) {
    }
  }


  public class LoadMovieFavoriteData extends AsyncTask<Void, Void, Void> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      showLoading(true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
      ArrayList<Movie> data = movieFavoriteHelper.selectMovie();
      movieAdapter.setData(data);
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      movieAdapter.notifyDataSetChanged();
      showLoading(false);
    }
  }
}