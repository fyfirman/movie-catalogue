package com.fyfirman.consumerapp;

import static android.provider.BaseColumns._ID;
import static com.fyfirman.consumerapp.DatabaseContract.FavoriteMovieColumns.OVERVIEW;
import static com.fyfirman.consumerapp.DatabaseContract.FavoriteMovieColumns.POSTER_PATH;
import static com.fyfirman.consumerapp.DatabaseContract.FavoriteMovieColumns.TITLE;

import android.database.Cursor;
import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<FavoriteMovieItem> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<FavoriteMovieItem> favoriteMovieList = new ArrayList<>();
        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(_ID));
            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(TITLE));
            String overview = notesCursor.getString(notesCursor.getColumnIndexOrThrow(OVERVIEW));
            String posterPath = notesCursor.getString(notesCursor.getColumnIndexOrThrow(POSTER_PATH));
            favoriteMovieList.add(new FavoriteMovieItem(id, title, overview, posterPath));
        }
        return favoriteMovieList;
    }
}
