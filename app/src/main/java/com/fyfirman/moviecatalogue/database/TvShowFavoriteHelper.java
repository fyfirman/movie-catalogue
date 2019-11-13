package com.fyfirman.moviecatalogue.database;

import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.id;
import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.overview;
import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.poster_path;
import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.table_name;
import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.name;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.fyfirman.moviecatalogue.data.Movie;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import java.util.ArrayList;

public class TvShowFavoriteHelper {
  private static final String DATABASE_TABLE = table_name;
  private static MovieFavoriteDatabaseHelper movieDatabaseHelper;
  private static TvShowFavoriteHelper movieFavoriteHelper;
  private static SQLiteDatabase sqLiteDatabase;

  private TvShowFavoriteHelper(Context context) {
    movieDatabaseHelper = new MovieFavoriteDatabaseHelper(context);
  }

  public static TvShowFavoriteHelper getInstance(Context context) {
    if (movieFavoriteHelper == null) {
      synchronized (SQLiteOpenHelper.class) {
        if (movieFavoriteHelper == null) {
          movieFavoriteHelper = new TvShowFavoriteHelper(context);
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

  public ArrayList<Tv_Show> selectTvShow() {
    ArrayList<Tv_Show> arrayList = new ArrayList<>();

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
        movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(name)));
        movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(overview)));
        movie.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(poster_path)));

        arrayList.add(movie);
        cursor.moveToNext();

      } while (!cursor.isAfterLast());
    }
    cursor.close();
    return arrayList;
  }

  public ArrayList<Movie> selectTvShow(String movie_id) {
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

    Tv_Show tv_show;

    if (cursor.getCount() > 0) {
      do {
        tv_show = new Tv_Show();
        tv_show.setId(cursor.getInt(cursor.getColumnIndexOrThrow(id)));
        tv_show.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(name)));
        tv_show.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(overview)));
        tv_show.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(poster_path)));

        arrayList.add(tv_show);
        cursor.moveToNext();

      } while (!cursor.isAfterLast());
    }
    cursor.close();
    return arrayList;
  }

  public void insertTvShow(Tv_Show tv_show) {
    sqLiteDatabase = movieDatabaseHelper.getWritableDatabase();

    ContentValues args = new ContentValues();
    args.put(id, tv_show.getId());
    args.put(name, tv_show.getTitle());
    args.put(overview, tv_show.getOverview());
    args.put(poster_path, tv_show.getPoster_path().substring(31)); //31 is last link character
    sqLiteDatabase.insert(DATABASE_TABLE, null, args);
  }

  public void deleteTvShow(String tv_show_id) {
    sqLiteDatabase = movieDatabaseHelper.getWritableDatabase();
    sqLiteDatabase.delete(table_name, id + " = '" + tv_show_id + "'", null);
  }
}
