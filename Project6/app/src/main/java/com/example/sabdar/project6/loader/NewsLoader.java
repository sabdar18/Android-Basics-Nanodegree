package com.example.sabdar.project6.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.sabdar.project6.pojo.News;
import com.example.sabdar.project6.utils.QueryUtils;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private Context mContext;
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mContext = context;
        mUrl = url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<News> newsList = QueryUtils.fetchNewsData(mUrl);
        return newsList;
    }
}
