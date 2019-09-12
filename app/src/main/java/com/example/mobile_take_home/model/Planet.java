package com.example.mobile_take_home.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Planet implements Parcelable {

    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public Planet() {
    }

    protected Planet(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Planet> CREATOR = new Parcelable.Creator<Planet>() {
        @Override
        public Planet createFromParcel(Parcel source) {
            return new Planet(source);
        }

        @Override
        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };
}
