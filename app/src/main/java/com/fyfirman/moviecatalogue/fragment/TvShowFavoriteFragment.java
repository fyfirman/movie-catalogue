package com.fyfirman.moviecatalogue.fragment;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.fyfirman.moviecatalogue.adapter.ListTvShowAdapter;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import com.fyfirman.moviecatalogue.database.TvShowFavoriteHelper;
import java.util.ArrayList;

public class TvShowFavoriteFragment extends Fragment {

  private static final String EXTRA_STATE = "EXTRA_STATE";
  private RecyclerView rvFavTvShow;
  private ArrayList<Tv_Show> listTvShow = new ArrayList<>();
  private ListTvShowAdapter tvShowAdapter;
  private ProgressBar progressBar;
  private TvShowFavoriteHelper tvShowFavoriteHelper;

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

    tvShowFavoriteHelper = TvShowFavoriteHelper.getInstance(getContext());

    if (savedInstanceState == null) {
      new LoadTvShowFavoriteData().execute();
    } else {
      ArrayList<Tv_Show> tv_show = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
      if (tv_show != null) {
        tvShowAdapter.setData(tv_show);
        tvShowAdapter.notifyDataSetChanged();
      }
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    new LoadTvShowFavoriteData().execute();
  }

  private void showRecyclerList(View view) {
    //mencari referensi recycle view
    rvFavTvShow = view.findViewById(R.id.rv_movie_favorite);
    rvFavTvShow.setHasFixedSize(true);

    //set layout manager
    rvFavTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));

    //set adapter
    tvShowAdapter = new ListTvShowAdapter(getContext(), listTvShow);
    rvFavTvShow.setAdapter(tvShowAdapter);

    //buat onclick callback function
    tvShowAdapter.setOnItemClickCallback(new ListTvShowAdapter.OnItemClickCallback() {
      @Override
      public void onItemClicked(Tv_Show data) {
        showSelectedMovie(data);
      }
    });
  }

  private void showSelectedMovie(Tv_Show data) {
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

  public class LoadTvShowFavoriteData extends AsyncTask<Void, Void, Void> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      showLoading(true);
    }

    @Override
    protected Void doInBackground(Void... voids) {
      ArrayList<Tv_Show> data = tvShowFavoriteHelper.selectTvShow();
      tvShowAdapter.setData(data);
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      super.onPostExecute(aVoid);
      tvShowAdapter.notifyDataSetChanged();
      showLoading(false);
    }
  }
}