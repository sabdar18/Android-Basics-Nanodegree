package com.example.sabdar.project4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.sabdar.project4.R;
import com.example.sabdar.project4.adapters.GenreAdapter;
import com.example.sabdar.project4.data.DataResource;
import com.example.sabdar.project4.pojo.Genre;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenreActivity extends AppCompatActivity {

    //initialize and refer Genre Grid View
    @BindView(R.id.genreList)
    GridView genreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        ButterKnife.bind(this);

        //get Genre Names from String Array
        String[] mGenreNames = getResources().getStringArray(R.array.genreList);

        ArrayList<Genre> mGenres = new ArrayList<>();
        mGenres.add(new Genre(mGenreNames[0], DataResource.genreImages[0]));
        mGenres.add(new Genre(mGenreNames[1], DataResource.genreImages[1]));
        mGenres.add(new Genre(mGenreNames[2], DataResource.genreImages[2]));
        mGenres.add(new Genre(mGenreNames[3], DataResource.genreImages[3]));
        mGenres.add(new Genre(mGenreNames[4], DataResource.genreImages[4]));
        mGenres.add(new Genre(mGenreNames[5], DataResource.genreImages[5]));
        mGenres.add(new Genre(mGenreNames[6], DataResource.genreImages[6]));
        mGenres.add(new Genre(mGenreNames[7], DataResource.genreImages[7]));
        mGenres.add(new Genre(mGenreNames[8], DataResource.genreImages[8]));
        mGenres.add(new Genre(mGenreNames[9], DataResource.genreImages[9]));

        GenreAdapter genreAdapter = new GenreAdapter(this, mGenres);
        genreList.setAdapter(genreAdapter);

    }


}
