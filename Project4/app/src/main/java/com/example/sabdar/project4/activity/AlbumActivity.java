package com.example.sabdar.project4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.sabdar.project4.R;
import com.example.sabdar.project4.adapters.AlbumAdapter;
import com.example.sabdar.project4.data.DataResource;
import com.example.sabdar.project4.pojo.Album;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumActivity extends AppCompatActivity {

    //initialize and refer Artist Grid View
    @BindView(R.id.albumList)
    ListView albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        ButterKnife.bind(this);

        //initialize Album Names from String Array
        String[] mAlbumNames = getResources().getStringArray(R.array.albumList);

        ArrayList<Album> mAlbums = new ArrayList<>();
        mAlbums.add(new Album(mAlbumNames[0], DataResource.albumImages[0], 12));
        mAlbums.add(new Album(mAlbumNames[1], DataResource.albumImages[1], 7));
        mAlbums.add(new Album(mAlbumNames[2], DataResource.albumImages[2], 6));
        mAlbums.add(new Album(mAlbumNames[3], DataResource.albumImages[3], 35));
        mAlbums.add(new Album(mAlbumNames[4], DataResource.albumImages[4], 7));
        mAlbums.add(new Album(mAlbumNames[5], DataResource.albumImages[5], 7));
        mAlbums.add(new Album(mAlbumNames[6], DataResource.albumImages[6], 7));
        mAlbums.add(new Album(mAlbumNames[7], DataResource.albumImages[7], 1));
        mAlbums.add(new Album(mAlbumNames[8], DataResource.albumImages[8], 1));
        mAlbums.add(new Album(mAlbumNames[9], DataResource.albumImages[9], 6));

        AlbumAdapter albumAdapter = new AlbumAdapter(this, mAlbums);
        albumList.setAdapter(albumAdapter);
    }
}
