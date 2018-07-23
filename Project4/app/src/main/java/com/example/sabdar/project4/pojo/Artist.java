package com.example.sabdar.project4.pojo;

public class Artist {
    private String mArtistName;
    private int mArtistImage;

    public Artist(String artistName, int artistImage) {
        this.mArtistName = artistName;
        this.mArtistImage = artistImage;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public int getArtistImage() {
        return mArtistImage;
    }
}
