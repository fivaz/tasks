package com.example.pontes_stefane_esig.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.adapters.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    RecyclerView rvTop;
    RecyclerView rvBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvTop = findViewById(R.id.rvTop);
        rvBottom = findViewById(R.id.rvBottom);

        initTopRecyclerView();
        initBottomRecyclerView();
    }

    private void initTopRecyclerView() {
        rvTop.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));

        List<String> topList = new ArrayList<>();
        topList.add("A");
        topList.add("B");

        ListAdapter topListAdapter = new ListAdapter(topList);
        rvTop.setAdapter(topListAdapter);
        rvTop.setOnDragListener(topListAdapter.getDragInstance());
    }

    private void initBottomRecyclerView() {
        rvBottom.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));

        List<String> bottomList = new ArrayList<>();
        bottomList.add("C");
        bottomList.add("D");

        ListAdapter bottomListAdapter = new ListAdapter(bottomList);
        rvBottom.setAdapter(bottomListAdapter);
        rvBottom.setOnDragListener(bottomListAdapter.getDragInstance());
    }
}