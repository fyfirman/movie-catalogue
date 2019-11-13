package com.fyfirman.moviecatalogue.database;

import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.id;
import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.name;
import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.overview;
import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.poster_path;
import static com.fyfirman.moviecatalogue.database.TvShowFavoriteDatabaseContract.TvShowDatabaseColumns.table_name;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.fyfirman.moviecatalogue.data.Tv_Show;
import java.util.ArrayList;

public class TvShowFavoriteHelper {
  private static final String DATABASE_TABLE = table_name;
  private static TvShowFavoriteDatabaseHelper tvShowFavoriteDatabaseHelper;
  private static TvShowFavoriteHelper tvShowFavoriteHelper;
  private static SQLiteDatabase sqLiteDatabase;

  private TvShowFavoriteHelper(Context context) {
    tvShowFavoriteDatabaseHelper = new TvShowFavoriteDatabaseHelper(context);
  }

  public static TvShowFavoriteHelper getInstance(Context context) {
    if (tvShowFavoriteHelper == null) {
      synchronized (SQLiteOpenHelper.class) {
        if (tvShowFavoriteHelper == null) {
          tvShowFavoriteHelper = new TvShowFavoriteHelper(context);
        }
      }
    }
    return tvShowFavoriteHelper;
  }

  public void close() {
    tvShowFavoriteDatabaseHelper.close();

    if (sqLiteDatabase.isOpen())
      sqLiteDatabase.close();
  }

  public ArrayList<Tv_Show> selectTvShow() {
    ArrayList<Tv_Show> arrayList = new ArrayList<>();

    sqLiteDatabase = tvShowFavoriteDatabaseHelper.getReadableDatabase();

    Cursor cursor = sqLiteDatabase.query(DATABASE_TABLE,
        null,
        null,
        null,
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

  public ArrayList<Tv_Show> selectTvShow(String tv_show_id) {
    ArrayList<Tv_Show> arrayList = new ArrayList<>();

    sqLiteDatabase = tvShowFavoriteDatabaseHelper.getReadableDatabase();

    String selection = id + " = ?";
    String[] selectionArgs = {tv_show_id};

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
    sqLiteDatabase = tvShowFavoriteDatabaseHelper.getWritableDatabase();

    ContentValues args = new ContentValues();
    args.put(id, tv_show.getId());
    args.put(name, tv_show.getTitle());
    args.put(overview, tv_show.getOverview());
    args.put(poster_path, tv_show.getPoster_path().substring(31)); //31 is last link character
    sqLiteDatabase.insert(DATABASE_TABLE, null, args);
  }

  public void deleteTvShow(String tv_show_id) {
    sqLiteDatabase = tvShowFavoriteDatabaseHelper.getWritableDatabase();
    sqLiteDatabase.delete(table_name, id + " = '" + tv_show_id + "'", null);
  }
}
