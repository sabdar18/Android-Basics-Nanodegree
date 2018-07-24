package com.example.sabdar.project4.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Placeholder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabdar.project4.R;
import com.example.sabdar.project4.activity.AlbumActivity;
import com.example.sabdar.project4.activity.ArtistActivity;
import com.example.sabdar.project4.pojo.Genre;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenreAdapter extends ArrayAdapter<Genre> {
    public GenreAdapter(@NonNull Context context, @NonNull List<Genre> genres) {
        super(context, 0, genres);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View itemListView = convertView;
        if (itemListView == null) {
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.genre_item, parent, false);
            holder = new ViewHolder(itemListView);
            itemListView.setTag(holder);

        } else {
            holder = (ViewHolder) itemListView.getTag();
        }

        Genre genre = getItem(position);
        holder.genreName.setText(genre.getGenreName());
        holder.genreImage.setContentDescription(genre.getGenreName());
        holder.genreImage.setImageResource(genre.getGenreImage());
        // to show Albums list
        itemListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlbumActivity.class);
                getContext().startActivity(intent);
            }
        });
        return itemListView;
    }

    static class ViewHolder {
        @BindView(R.id.genreTextView)
        TextView genreName;
        @BindView(R.id.genreImageView)
        ImageView genreImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
