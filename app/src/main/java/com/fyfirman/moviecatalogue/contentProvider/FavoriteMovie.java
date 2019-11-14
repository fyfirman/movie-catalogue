package com.fyfirman.moviecatalogue.contentProvider;

import static android.provider.BaseColumns._ID;
import static com.fyfirman.moviecatalogue.contentProvider.DatabaseContract.getColumnInt;
import static com.fyfirman.moviecatalogue.contentProvider.DatabaseContract.getColumnString;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteMovie implements Parcelable {
    private int id;
    private String title;
    private String overview;
    private String posterPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
    }

    public FavoriteMovie() {

    }

    public FavoriteMovie(int id, String title, String releaseDate, String voteAverage,
                         String overview, String posterPath) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public FavoriteMovie(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, DatabaseContract.FavoriteMovieColumns.TITLE);
        this.overview = getColumnString(cursor, DatabaseContract.FavoriteMovieColumns.OVERVIEW);
        this.posterPath = getColumnString(cursor, DatabaseContract.FavoriteMovieColumns.POSTER_PATH);
    }

    private FavoriteMovie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<FavoriteMovie> CREATOR = new Parcelable.Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel source) {
            return new FavoriteMovie(source);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };
}
