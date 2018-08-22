package com.example.sabdar.project5.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.sabdar.project5.R;
import com.example.sabdar.project5.adapter.ViewPagerAdapter;
import com.example.sabdar.project5.fragments.HotelsFragment;
import com.example.sabdar.project5.fragments.PlacesFragment;
import com.example.sabdar.project5.fragments.RestaurantsFragment;
import com.example.sabdar.project5.fragments.ShoppingFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.tabs)
    public TabLayout tabLayout;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    //adding tab titles, fragments to the adapter
    private void setupViewPager(ViewPager viewPager) {
        String[] tabs = getResources().getStringArray(R.array.tabs);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlacesFragment(), tabs[0]);
        adapter.addFragment(new RestaurantsFragment(), tabs[1]);
        adapter.addFragment(new HotelsFragment(), tabs[2]);
        adapter.addFragment(new ShoppingFragment(), tabs[3]);
        viewPager.setAdapter(adapter);
    }
}
