package com.example.sabdar.project4.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.sabdar.project4.R;
import com.example.sabdar.project4.adapters.AlbumAdapter;
import com.example.sabdar.project4.adapters.PlaylistAdapter;
import com.example.sabdar.project4.data.DataResource;
import com.example.sabdar.project4.pojo.Album;
import com.example.sabdar.project4.pojo.Playlist;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistActivity extends AppCompatActivity {

    //initialize and refer Artist Grid View
    @BindView(R.id.playlistList)
    ListView playlistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        ButterKnife.bind(this);

        //initialize Playlist, Artist  Names from String Array
        String[] mPlayListNames = getResources().getStringArray(R.array.genreList);
        String[] mArtistNames = getResources().getStringArray(R.array.artistList);

        ArrayList<Playlist> mPlaylists = new ArrayList<>();
        mPlaylists.add(new Playlist(mPlayListNames[0], DataResource.albumImages[0], 12, mArtistNames[0]));
        mPlaylists.add(new Playlist(mPlayListNames[1], DataResource.albumImages[1], 7, mArtistNames[1]));
        mPlaylists.add(new Playlist(mPlayListNames[2], DataResource.albumImages[2], 20, mArtistNames[2]));
        mPlaylists.add(new Playlist(mPlayListNames[3], DataResource.albumImages[3], 2, mArtistNames[3]));
        mPlaylists.add(new Playlist(mPlayListNames[4], DataResource.albumImages[4], 22, mArtistNames[4]));
        mPlaylists.add(new Playlist(mPlayListNames[5], DataResource.albumImages[5], 42, mArtistNames[5]));
        mPlaylists.add(new Playlist(mPlayListNames[6], DataResource.albumImages[6], 45, mArtistNames[6]));
        mPlaylists.add(new Playlist(mPlayListNames[7], DataResource.albumImages[7], 65, mArtistNames[7]));
        mPlaylists.add(new Playlist(mPlayListNames[8], DataResource.albumImages[8], 34, mArtistNames[8]));
        mPlaylists.add(new Playlist(mPlayListNames[9], DataResource.albumImages[9], 11, mArtistNames[9]));

        PlaylistAdapter playlistAdapter = new PlaylistAdapter(this, mPlaylists);
        playlistList.setAdapter(playlistAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
