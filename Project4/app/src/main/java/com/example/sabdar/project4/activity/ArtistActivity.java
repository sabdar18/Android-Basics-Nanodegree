package com.example.sabdar.project4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.sabdar.project4.R;
import com.example.sabdar.project4.adapters.ArtistAdapter;
import com.example.sabdar.project4.data.DataResource;
import com.example.sabdar.project4.pojo.Artist;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistActivity extends AppCompatActivity {

    //initialize and refer Artist Grid View
    @BindView(R.id.artistList)
    GridView artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        ButterKnife.bind(this);

        //get Artist Names from String Array
        String[] mArtistNames = getResources().getStringArray(R.array.artistList);

        ArrayList<Artist> mArtists = new ArrayList<>();
        mArtists.add(new Artist(mArtistNames[0], DataResource.artistImages[0]));
        mArtists.add(new Artist(mArtistNames[1], DataResource.artistImages[1]));
        mArtists.add(new Artist(mArtistNames[2], DataResource.artistImages[2]));
        mArtists.add(new Artist(mArtistNames[3], DataResource.artistImages[3]));
        mArtists.add(new Artist(mArtistNames[4], DataResource.artistImages[4]));
        mArtists.add(new Artist(mArtistNames[5], DataResource.artistImages[5]));
        mArtists.add(new Artist(mArtistNames[6], DataResource.artistImages[6]));
        mArtists.add(new Artist(mArtistNames[7], DataResource.artistImages[7]));
        mArtists.add(new Artist(mArtistNames[8], DataResource.artistImages[8]));
        mArtists.add(new Artist(mArtistNames[9], DataResource.artistImages[9]));

        ArtistAdapter artistAdapter = new ArtistAdapter(this, mArtists);
        artistList.setAdapter(artistAdapter);

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
