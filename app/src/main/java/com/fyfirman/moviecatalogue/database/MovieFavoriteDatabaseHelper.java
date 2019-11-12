package com.fyfirman.moviecatalogue.database;

import static com.fyfirman.moviecatalogue.database.MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.table_name;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieFavoriteDatabaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "moviefav.db";

  private static final int DATABASE_VERSION = 1;

  private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s "
          + " ( %s TEXT," +
          " %s TEXT," +
          " %s TEXT," +
          " %s TEXT)",
      table_name,
      MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.id,
      MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.title,
      MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.overview,
      MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.poster_path
  );

  MovieFavoriteDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_name);
    onCreate(sqLiteDatabase);
  }
}
