package com.example.sabdar.project4.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabdar.project4.R;
import com.example.sabdar.project4.pojo.Playlist;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {


    public PlaylistAdapter(@NonNull Context context, @NonNull List<Playlist> playlists) {
        super(context, 0, playlists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View itemListView = convertView;
        if (itemListView == null) {
            itemListView = LayoutInflater.from(getContext()).inflate(R.layout.playlist_item, parent, false);
            holder = new ViewHolder(itemListView);
            itemListView.setTag(holder);

        } else {
            holder = (ViewHolder) itemListView.getTag();
        }

        Playlist playlist = getItem(position);
        holder.playlistName.setText(playlist.getPlaylistName());
        holder.playlistSubTitle.setText(playlist.getPlaylistSubTitle());
        holder.playlistImage.setContentDescription(playlist.getPlaylistName());
        holder.playlistImage.setImageResource(playlist.getPlaylistImage());
        holder.playlistArtistName.setText(playlist.getArtistName());
        return itemListView;
    }

    static class ViewHolder {
        @BindView(R.id.playlistTextView)
        TextView playlistName;
        @BindView(R.id.playlistSubTitleTV)
        TextView playlistSubTitle;
        @BindView(R.id.playlistArtistNameTV)
        TextView playlistArtistName;
        @BindView(R.id.playlistImageView)
        ImageView playlistImage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
