package com.example.peter.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mWeekNr = new ArrayList<>();
    private ArrayList<ArrayList<String>> mDates = new ArrayList<>();
    private int dateNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateNumber = 1;
        Log.d(TAG, "onCreate: Created");

        initContentArrays();
    }

    private ArrayList<String> makeWeekDates()
    {
        Log.d(TAG, "makeWeekDates: Making arrays");
        int upperDays = 31;
        ArrayList<String> dateArray = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            dateArray.add(String.valueOf(dateNumber));
            if(dateNumber >= upperDays){
                dateNumber = 1;
            } else {
                dateNumber ++;
            }

        }
        return dateArray;
    }

    private void initContentArrays()
    {
        Log.d(TAG, "initContentArrays: Making Arrays");
        int weekAmount = 5;
        for(int i = 0; i < weekAmount; i++){
            mWeekNr.add("Week Number " + (i + 1));
            mDates.add(makeWeekDates());
        }
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        Log.d(TAG, "initRecyclerView: Making View");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mWeekNr, mDates, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
