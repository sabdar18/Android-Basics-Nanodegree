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
import com.example.sabdar.project4.pojo.Album;

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
