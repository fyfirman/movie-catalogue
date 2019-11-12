package com.fyfirman.moviecatalogue.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.adapter.MainFragmentPagerAdapter;

public class ExploreFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_explore, container, false);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    initTabLayout();
  }

  private void initTabLayout() {
    // setting toolbar
    Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
    ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

    // setting view pager
    ViewPager viewPager = getActivity().findViewById(R.id.view_pager);
    setupViewPager(viewPager);

    // setting tabLayout
    TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupViewPager(ViewPager viewPager) {
    MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(
        getChildFragmentManager());
    mainFragmentPagerAdapter.addFragment(new MoviesFragment(), getString(R.string.movies));
    mainFragmentPagerAdapter.addFragment(new TvShowFragment(), getString(R.string.tv_show));
    viewPager.setAdapter(mainFragmentPagerAdapter);
  }

}
