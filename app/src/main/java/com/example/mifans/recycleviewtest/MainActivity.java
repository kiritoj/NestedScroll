package com.example.mifans.recycleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    MyAdapter adapter;
    private Button addOne;
    private Button lessOne;
    private Button addN;
    private Button lessN;
    List<String> strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        addOne = findViewById(R.id.add_one_bt);
        addN = findViewById(R.id.add_n_bt);
        lessOne = findViewById(R.id.less_one_bt);
        lessN = findViewById(R.id.less_n_bt);
        addOne.setOnClickListener(this);
        addN.setOnClickListener(this);
        lessOne.setOnClickListener(this);
        lessN.setOnClickListener(this);
        strings = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        for (int i = 0; i < 50; i++) {
            strings.add("item "+i);
        }
        adapter = new MyAdapter(this,strings);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_one_bt:
                adapter.addOne("item "+strings.size());

                break;
            case R.id.add_n_bt:
                List<String> list = new ArrayList<>(3);
                for (int i = 0; i < 3; i++) {
                    list.add("item:"+(strings.size()+i));
                }
                adapter.addN(list);
                break;
            case R.id.less_one_bt:
                adapter.lessOne();
                break;
            case R.id.less_n_bt:
                adapter.lessN();
                break;
            default:
                break;
        }

    }
}
