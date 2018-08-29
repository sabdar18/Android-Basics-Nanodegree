package com.example.sabdar.project6.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sabdar.project6.BuildConfig;
import com.example.sabdar.project6.R;
import com.example.sabdar.project6.adapter.NewsAdapter;
import com.example.sabdar.project6.loader.NewsLoader;
import com.example.sabdar.project6.pojo.News;
import com.example.sabdar.project6.utils.QueryUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.*;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String API_KEY = BuildConfig.GuardianAPIKey;
    private static final String NEWS_FEED_URL = "https://content.guardianapis.com/search?q=debates&show-tags=contributor&api-key=" + API_KEY;
    private static final int NEWS_LOADER_ID = 1;


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.noNewsTextView)
    TextView noNewsTextView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private NewsAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        boolean isNetworkAvailable = QueryUtils.isNetworkAvailable(this);
        if (isNetworkAvailable) {
            setUpRecyclerView();
            noNewsTextView.setVisibility(INVISIBLE);
            getLoaderManager().initLoader(NEWS_LOADER_ID, null, this);
        } else {
            progressBar.setVisibility(INVISIBLE);
            noNewsTextView.setText(getResources().getString(R.string.no_network_text));
        }
    }

    private void setUpRecyclerView() {
        List<News> newsList = new ArrayList<>();
        mNewsAdapter = new NewsAdapter(newsList);
        LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mNewsAdapter);
    }

    //hide textview, progress bar , if data is loaded successfully
    private void hideViews() {
        progressBar.setVisibility(INVISIBLE);
        noNewsTextView.setVisibility(INVISIBLE);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, NEWS_FEED_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {
        hideViews();
        mNewsAdapter.clear();
        if (newsList != null && !newsList.isEmpty()) {
            mNewsAdapter.addAll(newsList);
        }else{
            noNewsTextView.setVisibility(VISIBLE);
            noNewsTextView.setText(getResources().getString(R.string.no_news_text));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsAdapter.clear();
    }
}
