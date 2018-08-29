package com.example.sabdar.project6.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sabdar.project6.R;
import com.example.sabdar.project6.pojo.News;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> mNewsList;
    public NewsAdapter(List<News> newsList) {
        this.mNewsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //store position to use in onclick method

        News news = mNewsList.get(position);
        holder.title.setText(news.getTitle());
        holder.section.setText(news.getSection());
        String author = news.getAuthor();
        if (author != null || !author.isEmpty()) {
            holder.author.setText(news.getAuthor());
        }
        holder.date.setText(formatDate(news.getPublishDate()));
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void addAll(List<News> items) {
        final int size = items.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mNewsList.add(items.get(i));
                notifyItemInserted(i);
            }
        }
    }

    public void clear() {
        final int size = mNewsList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mNewsList.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.titleTextView)
        public TextView title;
        @BindView(R.id.sectionTextView)
        public TextView section;
        @BindView(R.id.dateTextView)
        public TextView date;
        @BindView(R.id.authorTextView)
        public TextView author;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String url = mNewsList.get(position).getWebUrl();
            Log.i("intent log "+position, url);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            v.getContext().startActivity(intent);
        }
    }

    //format date
    private String formatDate(String publishDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = null;
        try {
            date = formatter.parse(publishDate.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy h:m a");

        return sdf.format(date);
    }

}
