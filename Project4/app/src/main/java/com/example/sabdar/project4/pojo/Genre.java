package com.example.sabdar.project4.pojo;

public class Genre {
    private String mGenreName;
    private int mGenreImage;

    public Genre(String genreName, int genreImage) {
        this.mGenreName = genreName;
        this.mGenreImage = genreImage;
    }

    public String getGenreName() {
        return mGenreName;
    }

    public int getGenreImage() {
        return mGenreImage;
    }
}
