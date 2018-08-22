package com.example.sabdar.project5.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sabdar.project5.R;
import com.example.sabdar.project5.adapter.LocationAdapter;
import com.example.sabdar.project5.data.MockData;
import com.example.sabdar.project5.pojo.Location;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;
    private LocationAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MockData mockData;

    private ArrayList<Location> mShopping;


    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mockData = new MockData(getContext());
        mShopping = mockData.getShopping();
        //for performance set fixed size
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new LocationAdapter(mShopping);
        recyclerView.setAdapter(mAdapter);
    }
}
