package com.fyfirman.moviecatalogue.database;

import android.provider.BaseColumns;

public class TvShowFavoriteDatabaseContract {
  static final class TvShowDatabaseColumns implements BaseColumns{
    static final String table_name = "tv_show_favorite";
    static final String id = "id";
    static final String name = "name";
    static final String overview = "overview";
    static final String poster_path = "poster_path";
  }
}
