package com.fyfirman.moviecatalogue.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements android.os.Parcelable {
  final String base_url = "https://image.tmdb.org/t/p/";
  final String poster_size = "w185/";

  private String poster_path;
  private String title;

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  private String overview;

  public Movie() {
  }

  public String getPoster_path() {
    return base_url+poster_size+poster_path;
  }

  public void setPoster_path(String poster_path) {
    this.poster_path = poster_path;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.poster_path);
    dest.writeString(this.title);
    dest.writeString(this.overview);
  }

  protected Movie(Parcel in) {
    this.title = in.readString();
    this.overview = in.readString();
    this.poster_path = in.readString();
  }

  public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
    @Override
    public Movie createFromParcel(Parcel source) {
      return new Movie(source);
    }

    @Override
    public Movie[] newArray(int size) {
      return new Movie[size];
    }
  };
}
