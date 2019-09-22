package com.fyfirman.moviecatalogue;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Movie implements android.os.Parcelable {

  private int photo;
  private String title;
  private String synopsis;

  public Movie() {
  }

  public int getPhoto() {
    return photo;
  }

  public void setPhoto(int photo) {
    this.photo = photo;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.photo);
    dest.writeString(this.title);
    dest.writeString(this.synopsis);
  }

  protected Movie(Parcel in) {
    this.photo = in.readInt();
    this.title = in.readString();
    this.synopsis = in.readString();
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
