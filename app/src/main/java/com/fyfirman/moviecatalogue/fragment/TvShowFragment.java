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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.activity.DetailTvShowActivity;
import com.fyfirman.moviecatalogue.adapter.ListTvShowAdapter;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import com.fyfirman.moviecatalogue.viewmodel.TvShowViewModel;
import java.util.ArrayList;

public class TvShowFragment extends Fragment {

  private RecyclerView rvTvShow;
  private ArrayList<Tv_Show> listTvShow = new ArrayList<>();
  private TvShowViewModel tvShowViewModel;
  private ListTvShowAdapter listTvShowAdapter;
  private ProgressBar progressBar;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
    showRecyclerList(view);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    progressBar = getView().findViewById(R.id.indeterminateBar);
    final EditText editTextSearch = getView().findViewById(R.id.et_search_tv_show);
    ImageButton btnSearchTvShow = getView().findViewById(R.id.btn_search_tv_show);

    showLoading(true);

    showRecyclerList(view);

    btnSearchTvShow.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        showLoading(true);
        String query = editTextSearch.getText().toString();

        if (query.length() > 0) {
          tvShowViewModel.searchMovie(query);
        } else {
          tvShowViewModel.setData(getResources().getString(R.string.language));
        }
      }
    });

  }

  private void showRecyclerList(View view) {
    //mencari referensi recycle view
    rvTvShow = view.findViewById(R.id.rv_tv_show);
    rvTvShow.setHasFixedSize(true);

    //set layout manager
    rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));

    //set adapter
    listTvShowAdapter = new ListTvShowAdapter(getContext(), listTvShow);
    rvTvShow.setAdapter(listTvShowAdapter);

    //buat onclick callback function
    listTvShowAdapter.setOnItemClickCallback(new ListTvShowAdapter.OnItemClickCallback() {
      @Override
      public void onItemClicked(Tv_Show data) {
        showSelectedTvShow(data);
      }
    });

    tvShowViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
        .get(TvShowViewModel.class);

    tvShowViewModel.setData(getResources().getString(R.string.language));

//    Observe
    tvShowViewModel.getData().observe(this, new Observer<ArrayList<Tv_Show>>() {
      @Override
      public void onChanged(ArrayList<Tv_Show> mModels) {
        if (mModels != null) {
          listTvShowAdapter.setData(mModels);
          showLoading(false);
        }
      }
    });
  }

  private void showSelectedTvShow(Tv_Show data) {
//    Toast toast = Toast.makeText(getActivity(),"Hello TV Show!",Toast.LENGTH_SHORT);
//    toast.show();

    Intent showDetailTvShow = new Intent(getActivity(), DetailTvShowActivity.class);

    showDetailTvShow.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data);
    startActivity(showDetailTvShow);
  }

  private void showLoading(Boolean state) {
    if (state) {
      progressBar.setVisibility(View.VISIBLE);
    } else {
      progressBar.setVisibility(View.GONE);
    }
  }
}