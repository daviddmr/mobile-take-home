package com.example.mobile_take_home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Character implements Parcelable {

    private long id;

    private String name;

    private String status;

    private String species;

    private String type;

    private String gender;

    private Planet origin;

    private Planet location;

    private String image;

    private ArrayList<String> episode;

    private String url;

    private String created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Planet getOrigin() {
        return origin;
    }

    public void setOrigin(Planet origin) {
        this.origin = origin;
    }

    public Planet getLocation() {
        return location;
    }

    public void setLocation(Planet location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getEpisode() {
        return episode;
    }

    public void setEpisode(ArrayList<String> episode) {
        this.episode = episode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.status);
        dest.writeString(this.species);
        dest.writeString(this.type);
        dest.writeString(this.gender);
        dest.writeParcelable(this.origin, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.image);
        dest.writeStringList(this.episode);
        dest.writeString(this.url);
        dest.writeString(this.created);
    }

    public Character() {
    }

    protected Character(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.status = in.readString();
        this.species = in.readString();
        this.type = in.readString();
        this.gender = in.readString();
        this.origin = in.readParcelable(Planet.class.getClassLoader());
        this.location = in.readParcelable(Planet.class.getClassLoader());
        this.image = in.readString();
        this.episode = in.createStringArrayList();
        this.url = in.readString();
        this.created = in.readString();
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
