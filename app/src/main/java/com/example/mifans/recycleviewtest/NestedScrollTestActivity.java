package com.example.mifans.recycleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NestedScrollTestActivity extends AppCompatActivity {
    List<String> stringList = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_test);
        recyclerView = findViewById(R.id.nested_rv);
        for (int i = 0; i < 60; i++) {
            stringList.add("item-"+i);
        }
        adapter = new MyAdapter(this,stringList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter = new MyAdapter(this,stringList);
        recyclerView.setAdapter(adapter);

    }
}
