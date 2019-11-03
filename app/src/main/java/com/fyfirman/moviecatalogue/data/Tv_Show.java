package com.fyfirman.moviecatalogue.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class Tv_Show implements android.os.Parcelable {
  private int photo;
  private String title;
  private String synopsis;

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

  public Tv_Show() {
  }

  protected Tv_Show(Parcel in) {
    this.photo = in.readInt();
    this.title = in.readString();
    this.synopsis = in.readString();
  }

  public static final Creator<Tv_Show> CREATOR = new Creator<Tv_Show>() {
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
