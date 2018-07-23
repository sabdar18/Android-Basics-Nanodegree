package com.example.sabdar.project4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabdar.project4.R;
import com.example.sabdar.project4.pojo.Artist;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistAdapter extends ArrayAdapter<Artist> {

    public ArtistAdapter(@NonNull Context context, @NonNull List<Artist> artists) {
        super(context, 0, artists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View itemListView = convertView;
        if (itemListView == null) {
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.artist_item, parent, false);
            holder = new ViewHolder(itemListView);
            itemListView.setTag(holder);

        } else {
            holder = (ViewHolder) itemListView.getTag();
        }

        Artist artist = getItem(position);
        holder.artistName.setText(artist.getArtistName());
        holder.artistImage.setContentDescription(artist.getArtistName());
        holder.artistImage.setImageResource(artist.getArtistImage());
        return itemListView;
    }

    static class ViewHolder {
        @BindView(R.id.artistTextView)
        TextView artistName;
        @BindView(R.id.artistImageView)
        ImageView artistImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
