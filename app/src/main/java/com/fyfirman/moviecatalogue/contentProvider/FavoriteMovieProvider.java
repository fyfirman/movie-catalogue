package com.fyfirman.moviecatalogue.contentProvider;

import static com.fyfirman.moviecatalogue.contentProvider.DatabaseContract.AUTHORITY;
import static com.fyfirman.moviecatalogue.contentProvider.DatabaseContract.FavoriteMovieColumns.CONTENT_URI;
import static com.fyfirman.moviecatalogue.contentProvider.DatabaseContract.FavoriteMovieColumns.TABLE_NAME;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import com.fyfirman.moviecatalogue.fragment.MoviesFavoriteFragment;

public class FavoriteMovieProvider extends ContentProvider {
    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private FavoriteMovieHelper favoriteMovieHelper;

    static {
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, NOTE);

        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", NOTE_ID);
    }

    @Override
    public boolean onCreate() {
        favoriteMovieHelper = FavoriteMovieHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        favoriteMovieHelper.open();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case NOTE:
                cursor = favoriteMovieHelper.queryProvider();
                break;
            case NOTE_ID:
                cursor = favoriteMovieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        favoriteMovieHelper.open();
        long added;
        switch (sUriMatcher.match(uri)) {
            case NOTE:
                added = favoriteMovieHelper.insertProvider(contentValues);
                break;
            default:
                added = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, new MoviesFavoriteFragment.DataObserver(new Handler(), getContext()));
        return Uri.parse(CONTENT_URI + "/" + added);
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        favoriteMovieHelper.open();
        int updated;
        switch (sUriMatcher.match(uri)) {
            case NOTE_ID:
                updated = favoriteMovieHelper.updateProvider(uri.getLastPathSegment(), contentValues);
                break;
            default:
                updated = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, new MoviesFavoriteFragment.DataObserver(new Handler(), getContext()));
        return updated;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        favoriteMovieHelper.open();
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case NOTE_ID:
                deleted = favoriteMovieHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, new MoviesFavoriteFragment.DataObserver(new Handler(), getContext()));
        return deleted;
    }
}
