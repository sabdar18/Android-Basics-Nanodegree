package com.example.sabdar.project4.pojo;


public class Album {
    private String mAlbumName;
    private int mAlbumImage;
    private int mSongCount;

    public Album(String albumName, int albumImage, int songCount) {
        this.mAlbumName = albumName;
        this.mAlbumImage = albumImage;
        this.mSongCount = songCount;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public int getAlbumImage() {
        return mAlbumImage;
    }

    public String getAlbumSubTitle() {
        return String.format("%d Songs", mSongCount);
    }

    public int getSongCount() {
        return mSongCount;
    }
}
