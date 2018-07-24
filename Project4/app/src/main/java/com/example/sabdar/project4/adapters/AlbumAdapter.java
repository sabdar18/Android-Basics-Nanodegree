package com.example.sabdar.project4.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabdar.project4.R;
import com.example.sabdar.project4.activity.AlbumActivity;
import com.example.sabdar.project4.activity.ArtistActivity;
import com.example.sabdar.project4.pojo.Album;
import com.example.sabdar.project4.pojo.Artist;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends ArrayAdapter<Album> {

    public AlbumAdapter(@NonNull Context context, @NonNull List<Album> albums) {
        super(context, 0, albums);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View itemListView = convertView;
        if (itemListView == null) {
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.album_item, parent, false);
            holder = new ViewHolder(itemListView);
            itemListView.setTag(holder);

        } else {
            holder = (ViewHolder) itemListView.getTag();
        }

        Album album = getItem(position);
        holder.albumName.setText(album.getAlbumName());
        holder.albumSubTitle.setText(album.getAlbumSubTitle());
        holder.albumImage.setContentDescription(album.getAlbumName());
        holder.albumImage.setImageResource(album.getAlbumImage());
        // to show artists list
        itemListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ArtistActivity.class);
                getContext().startActivity(intent);
            }
        });
        return itemListView;
    }

    static class ViewHolder {
        @BindView(R.id.albumTextView)
        TextView albumName;
        @BindView(R.id.albumSubTitleTV)
        TextView albumSubTitle;
        @BindView(R.id.albumImageView)
        ImageView albumImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
