package com.example.sabdar.project4.pojo;

public class Playlist {
    private String mPlaylistName;
    private int mPlaylistImage;
    private int mSongCount;
    private String mArtistName;

    public Playlist(String playlistName, int playlistImage, int songsCount, String artistName) {
        this.mPlaylistName = playlistName;
        this.mPlaylistImage = playlistImage;
        this.mSongCount = songsCount;
        this.mArtistName = artistName;
    }

    public String getPlaylistName() {
        return mPlaylistName;
    }

    public int getPlaylistImage() {
        return mPlaylistImage;
    }

    public String getPlaylistSubTitle() {
        return String.format("%d Songs", mSongCount);
    }

    public String getArtistName() {
        return mArtistName;
    }
}
