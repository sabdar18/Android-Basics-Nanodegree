package com.example.sabdar.project6.activity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SharedMemory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.*;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String API_KEY = BuildConfig.GuardianAPIKey;
    private static final String NEWS_FEED_URL = "https://content.guardianapis.com/search";
    private static final int NEWS_LOADER_ID = 1;


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.noNewsTextView)
    TextView noNewsTextView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindString(R.string.no_network_text)
    String noNetwork;
    @BindString(R.string.no_news_text)
    String noNewsAvailable;

    @BindString(R.string.settings_news_items_limit_key)
    String newsLimitKey;
    @BindString(R.string.settings_news_items_limit_default_value)
    String newsLimitDefaultValue;
    @BindString(R.string.settings_news_type_key)
    String newsTypeKey;
    @BindString(R.string.settings_news_type_default_value)
    String newsTypeDefaultValue;
    @BindString(R.string.settings_order_by_key)
    String orderByKey;
    @BindString(R.string.settings_order_by_default_value)
    String orderByDefaultValue;
    @BindString(R.string.settings_topic_key)
    String topicKey;
    @BindString(R.string.settings_topic_default_value)
    String topicDefaultValue;


    private NewsAdapter mNewsAdapter;
    private List<News> mNewsList;

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
            noNewsTextView.setText(noNetwork);
        }
    }

    private void setUpRecyclerView() {
        mNewsList = new ArrayList<>();
        mNewsAdapter = new NewsAdapter(mNewsList);
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

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String pageSize = sharedPrefs.getString(newsLimitKey, newsLimitDefaultValue);
        String newsType = sharedPrefs.getString(newsTypeKey, newsTypeDefaultValue);
        String orderBy = sharedPrefs.getString(orderByKey, orderByDefaultValue);
        String topic = sharedPrefs.getString(topicKey, topicDefaultValue);

        Uri uri = Uri.parse(NEWS_FEED_URL);
        Uri.Builder uriBuilder = uri.buildUpon();
        uriBuilder.appendQueryParameter("q", "debates");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", API_KEY);
        uriBuilder.appendQueryParameter("section",topic);
        uriBuilder.appendQueryParameter("page-size", pageSize);
        uriBuilder.appendQueryParameter("type", newsType);
        uriBuilder.appendQueryParameter("order-by", orderBy);

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsList) {
        mNewsList = newsList;
        hideViews();
        mNewsAdapter.clear();
        if (newsList != null && !newsList.isEmpty()) {
            mNewsAdapter.addAll(newsList);
        } else {
            noNewsTextView.setVisibility(VISIBLE);
            noNewsTextView.setText(noNewsAvailable);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
