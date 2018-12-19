package com.example.peter.app2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mWeekNr = new ArrayList<>();
    private ArrayList<ArrayList<String>> mDates = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> weekNr, ArrayList<ArrayList<String>> dates, Context context) {
        this.mWeekNr = weekNr;
        this.mDates = dates;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_weekdaytable, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.weekNr.setText(mWeekNr.get(i));

        ArrayList<String> myList = mDates.get(i);
        viewHolder.weekDate1.setText(myList.get(0));
        viewHolder.weekDate2.setText(myList.get(1));
        viewHolder.weekDate3.setText(myList.get(2));
        viewHolder.weekDate4.setText(myList.get(3));
        viewHolder.weekDate5.setText(myList.get(4));
        viewHolder.weekDate6.setText(myList.get(5));
        viewHolder.weekDate7.setText(myList.get(6));
    }

    @Override
    public int getItemCount() {
        return mWeekNr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView weekNr;
        TextView weekDate1;
        TextView weekDate2;
        TextView weekDate3;
        TextView weekDate4;
        TextView weekDate5;
        TextView weekDate6;
        TextView weekDate7;
        TableLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekNr = itemView.findViewById(R.id.textView_weekNr);
            weekDate1 = itemView.findViewById(R.id.textView_date1);
            weekDate2 = itemView.findViewById(R.id.textView_date2);
            weekDate3 = itemView.findViewById(R.id.textView_date3);
            weekDate4 = itemView.findViewById(R.id.textView_date4);
            weekDate5 = itemView.findViewById(R.id.textView_date5);
            weekDate6 = itemView.findViewById(R.id.textView_date6);
            weekDate7 = itemView.findViewById(R.id.textView_date7);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}