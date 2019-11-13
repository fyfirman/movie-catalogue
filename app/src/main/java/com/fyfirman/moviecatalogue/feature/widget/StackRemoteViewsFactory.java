package com.fyfirman.moviecatalogue.feature.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import com.fyfirman.moviecatalogue.R;
import com.fyfirman.moviecatalogue.data.Movie;
import com.fyfirman.moviecatalogue.database.MovieFavoriteHelper;
import java.util.List;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

  private List<Movie> movieFavorite;
  private final Context mContext;
  private MovieFavoriteHelper movieFavoriteHelper;

  StackRemoteViewsFactory(Context context) {
    mContext = context;
  }

  @Override
  public void onCreate() {
    movieFavoriteHelper = MovieFavoriteHelper.getInstance(mContext);
    movieFavorite = movieFavoriteHelper.selectMovie();
  }

  @Override
  public void onDataSetChanged() {
    movieFavorite = movieFavoriteHelper.selectMovie();
  }

  @Override
  public void onDestroy() {

  }

  @Override
  public int getCount() {
    return movieFavorite.size();
  }

  @Override
  public RemoteViews getViewAt(int position) {
    RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_widget);

    if (movieFavorite.size() > 0) {
      Movie movie = movieFavorite.get(position);

      try {
        Bitmap bitmap = Glide.with(mContext)
            .asBitmap()
            .load(movie.getPoster_path())
            .submit(512, 512)
            .get();

        remoteViews.setImageViewBitmap(R.id.imageView, bitmap);
      } catch (Exception e) {
        e.printStackTrace();
      }

      Bundle extras = new Bundle();
      extras.putString(FavoriteWidgetProvider.EXTRA_ITEM, movie.getTitle());
      Intent fillInIntent = new Intent();
      fillInIntent.putExtras(extras);
      remoteViews.setOnClickFillInIntent(R.id.imageView, fillInIntent);

      return remoteViews;
    } else {
      return null;
    }
  }

  @Override
  public RemoteViews getLoadingView() {
    return null;
  }

  @Override
  public int getViewTypeCount() {
    return 1;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }
}
