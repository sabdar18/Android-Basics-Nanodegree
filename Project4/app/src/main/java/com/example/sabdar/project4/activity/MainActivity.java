package com.example.sabdar.project4.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sabdar.project4.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    //method to display activity using Explicit Intent based on clicking TextView Ids
    @OnClick({R.id.albumsTV, R.id.playlistsTV, R.id.artistsTV, R.id.genresTV})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.albumsTV:
                Intent albumsIntent = new Intent(this, AlbumActivity.class);
                startActivity(albumsIntent);
                break;
            case R.id.playlistsTV:
                Intent playlistsIntent = new Intent(this, PlaylistActivity.class);
                startActivity(playlistsIntent);
                break;
            case R.id.artistsTV:
                Intent artistsIntent = new Intent(this, ArtistActivity.class);
                startActivity(artistsIntent);
                break;
            case R.id.genresTV:
                Intent genresIntent = new Intent(this, GenreActivity.class);
                startActivity(genresIntent);
                break;

        }
    }
}
