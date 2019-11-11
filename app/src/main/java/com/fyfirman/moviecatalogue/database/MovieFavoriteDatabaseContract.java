package com.fyfirman.moviecatalogue.database;

import android.provider.BaseColumns;

public class MovieFavoriteDatabaseContract {
  static final class MovieFavoriteDatabaseColumns implements BaseColumns{
    static final String table_name = "movie_favorite";
    static final String id = "id";
    static final String title = "title";
    static final String overview = "overview";
    static final String poster_path = "poster_path";
  }
}
