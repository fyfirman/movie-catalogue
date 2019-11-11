package com.fyfirman.moviecatalogue.database;

import static com.fyfirman.moviecatalogue.database.MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.table_name;
import static com.fyfirman.moviecatalogue.database.MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.id;
import static com.fyfirman.moviecatalogue.database.MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.title;
import static com.fyfirman.moviecatalogue.database.MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.overview;
import static com.fyfirman.moviecatalogue.database.MovieFavoriteDatabaseContract.MovieFavoriteDatabaseColumns.poster_path;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.fyfirman.moviecatalogue.data.Movie;
import java.util.ArrayList;

public class MovieFavoriteHelper {
  private static final String DATABASE_TABLE = table_name;
  private static MovieFavoriteDatabaseHelper movieDatabaseHelper;
  private static MovieFavoriteHelper movieFavoriteHelper;
  private static SQLiteDatabase sqLiteDatabase;

  private MovieFavoriteHelper(Context context) {
    movieDatabaseHelper = new MovieFavoriteDatabaseHelper(context);
  }

  public static MovieFavoriteHelper getInstance(Context context) {
    if (movieFavoriteHelper == null) {
      synchronized (SQLiteOpenHelper.class) {
        if (movieFavoriteHelper == null) {
          movieFavoriteHelper = new MovieFavoriteHelper(context);
        }
      }
    }
    return movieFavoriteHelper;
  }

  public void close() {
    movieDatabaseHelper.close();

    if (sqLiteDatabase.isOpen())
      sqLiteDatabase.close();
  }

  public ArrayList<Movie> selectMovie() {
    ArrayList<Movie> arrayList = new ArrayList<>();

    sqLiteDatabase = movieDatabaseHelper.getReadableDatabase();

    Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,
        null,
        null,
        null,
        null,
        null,
        null,
        null);

    cursor.moveToFirst();

    Movie movie;

    if (cursor.getCount() > 0) {
      do {
        movie = new Movie();
        movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(id)));
        movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(title)));
        movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(overview)));
        movie.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(poster_path)));

        arrayList.add(movie);
        cursor.moveToNext();

      } while (!cursor.isAfterLast());
    }
    cursor.close();
    return arrayList;
  }

  public ArrayList<Movie> selectMovie(String movie_id) {
    ArrayList<Movie> arrayList = new ArrayList<>();

    sqLiteDatabase = movieDatabaseHelper.getReadableDatabase();

    String selection = id + " = ?";
    String[] selectionArgs = {movie_id};

    Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE, null,
        selection,
        selectionArgs,
        null,
        null,
        null,
        null);

    cursor.moveToFirst();

    Movie movie;

    if (cursor.getCount() > 0) {
      do {
        movie = new Movie();
        movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(id)));
        movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(title)));
        movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(overview)));
        movie.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(poster_path)));

        arrayList.add(movie);
        cursor.moveToNext();

      } while (!cursor.isAfterLast());
    }
    cursor.close();
    return arrayList;
  }

  public void insertMovie(Movie movie) {
    sqLiteDatabase = movieDatabaseHelper.getWritableDatabase();

    ContentValues args = new ContentValues();
    args.put(id, movie.getId());
    args.put(title, movie.getTitle());
    args.put(overview, movie.getOverview());
    args.put(poster_path, movie.getPoster_path());
    sqLiteDatabase.insert(DATABASE_TABLE, null, args);
  }

  public void deleteMovie(String movie_id) {
    sqLiteDatabase = movieDatabaseHelper.getWritableDatabase();
    sqLiteDatabase.delete(table_name, id + " = '" + movie_id + "'", null);
  }
}
