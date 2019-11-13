package com.fyfirman.moviecatalogue.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Tv_Show implements Parcelable {

  final String base_url = "https://image.tmdb.org/t/p/";
  final String poster_size = "w185/";

  private Integer id;
  private String poster_path;
  private String title;
  private String overview;

  public Tv_Show() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public String getPoster_path() {
    return base_url + poster_size + poster_path;
  }

  public void setPoster_path(String poster_path) {
    this.poster_path = poster_path;
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
    dest.writeString(this.poster_path);
  }

  protected Tv_Show(Parcel in) {
    this.id = in.readInt();
    this.title = in.readString();
    this.overview = in.readString();
    this.poster_path = in.readString();
  }

  public static final Parcelable.Creator<Tv_Show> CREATOR = new Parcelable.Creator<Tv_Show>() {
    @Override
    public Tv_Show createFromParcel(Parcel source) {
      return new Tv_Show(source);
    }

    @Override
    public Tv_Show[] newArray(int size) {
      return new Tv_Show[size];
    }
  };
}
