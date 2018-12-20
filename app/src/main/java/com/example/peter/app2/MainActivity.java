package com.example.peter.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mWeekNr = new ArrayList<>();
    private ArrayList<ArrayList<String>> mDates = new ArrayList<>();
    private int dateNumber;
    private String myHttpString;
    public TextView textView_users;
    private String folderolURL = "http://www.folderol.dk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateNumber = 1;
        Log.d(TAG, "onCreate: Created");
        textView_users = findViewById(R.id.textView_users);
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

    public void startHttpReq(View view)
    {
        String thisURL = folderolURL + "";
        makeHttpReq(thisURL);
        // Min Url
    }
    public void httpCallback(String response)
    {
        textView_users.setText(response);
    }

    public void makeHttpReq(String url)
    {
        Log.d(TAG, "getHttpReq: Fired OkHttp Method");
        Log.d(TAG, "getHttpReq: Building Client");
        OkHttpClient client = new OkHttpClient();
        Log.d(TAG, "getHttpReq: Building Request");
        Request request = new Request.Builder()
                .url(url)
                .build();

        Log.d(TAG, "getHttpReq: Firing Call");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "getHttpReq: Failure");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "getHttpReq: Response");
                if(response.isSuccessful()){
                    Log.d(TAG, "getHttpReq: Response successful");
                    final String mResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            httpCallback(mResponse);
                        }
                    });
                }
            }
        });
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
