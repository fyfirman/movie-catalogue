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
import com.fyfirman.moviecatalogue.activity.DetailTvShowActivity;
import com.fyfirman.moviecatalogue.adapter.ListTvShowAdapter;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import java.util.ArrayList;

public class TvShowFragment extends Fragment {

  private RecyclerView rvTvShow;
  private ArrayList<Tv_Show> listTvShow = new ArrayList<>();

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
    showRecyclerList(view);

    return view;
  }

  private void initTvShow() {
  }


  public ArrayList<Tv_Show> getListTvShow() {
    String[] dataTitle = getResources().getStringArray(R.array.tv_show_title);
    String[] dataSynopsis = getResources().getStringArray(R.array.tv_show_synopsis);
    TypedArray dataPhoto = getResources().obtainTypedArray(R.array.tv_show_photo);

    ArrayList<Tv_Show> listTvShow = new ArrayList<>();
    for (int i = 0; i < dataTitle.length; i++) {
      Tv_Show tv_show = new Tv_Show();
      tv_show.setTitle(dataTitle[i]);
      tv_show.setSynopsis(dataSynopsis[i]);
      tv_show.setPhoto(dataPhoto.getResourceId(i, 1));
      listTvShow.add(tv_show);
    }
    return listTvShow;
  }

  private void showRecyclerList(View view) {
    //mencari referensi recycle view
    rvTvShow = view.findViewById(R.id.rv_tv_show);
    rvTvShow.setHasFixedSize(true);

    //mengambil data
    listTvShow.addAll(getListTvShow());

    //set layout manager
    rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));

    //set adapter
    ListTvShowAdapter listTvShowAdapter = new ListTvShowAdapter(listTvShow);
    rvTvShow.setAdapter(listTvShowAdapter);

    //buat onclick callback function
    listTvShowAdapter.setOnItemClickCallback(new ListTvShowAdapter.OnItemClickCallback() {
      @Override
      public void onItemClicked(Tv_Show data) {
        showSelectedTvShow(data);
      }
    });
  }

  private void showSelectedTvShow(Tv_Show data) {
//    Toast toast = Toast.makeText(getActivity(),"Hello TV Show!",Toast.LENGTH_SHORT);
//    toast.show();

    Intent showDetailMovie = new Intent(getActivity(), DetailTvShowActivity.class);

    showDetailMovie.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data);
    startActivity(showDetailMovie);
  }
}