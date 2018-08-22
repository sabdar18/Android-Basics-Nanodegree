package com.example.sabdar.project5.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.Guideline;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.sabdar.project5.R;
import com.example.sabdar.project5.pojo.Location;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private ArrayList<Location> mDataSet;

    public LocationAdapter(ArrayList<Location> data) {
        mDataSet = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.titleTextView)
        public TextView title;
        @BindView(R.id.subTitleTextView)
        public TextView subTitle;
        @BindView(R.id.imageView)
        public ImageView imageView;
        @BindView(R.id.ratingBar)
        public RatingBar ratingBar;
        @BindView(R.id.ratingTextView)
        public TextView rating;
        @BindView(R.id.phoneTextView)
        public TextView phone;
        @BindView(R.id.addressTextView)
        public TextView address;
        @BindView(R.id.descriptionTextView)
        public TextView description;
        @BindView(R.id.guideline)
        public Guideline guideline;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Location location = mDataSet.get(i);
        viewHolder.title.setText(location.getTitle());
        viewHolder.subTitle.setText(location.getSubTitle());
        viewHolder.rating.setText(String.valueOf(location.getRating()));
        viewHolder.phone.setText(location.getPhone());
        viewHolder.address.setText(location.getAddress());
        viewHolder.description.setText(location.getDescription());
        viewHolder.ratingBar.setRating((float) location.getRating());
        if (location.hasImage()) {
            viewHolder.imageView.setImageResource(location.getImage());
        } else {
            viewHolder.imageView.setVisibility(View.GONE);
            viewHolder.guideline.setGuidelineBegin(0);
        }

    }
}
