package com.example.peter.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mDays = new ArrayList<>();
    private String folderolURL = "http://www.folderol.dk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Created");
        initContentArrays();
    }

    private void initContentArrays()
    {
        Log.d(TAG, "initContentArrays: Making Arrays");

        for(int i = 0; i < 64; i++){
            mDates.add(i + 1 + "");
            mDays.add("Dab");
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
        // Response is HTTP Response
        // Not Used, saved for convenience
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
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mDates, mDays, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
