package com.smart.cst.afimail.plan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.cst.afimail.R;
import com.smart.cst.afimail.app.AppConfig;
import com.smart.cst.afimail.timeline.ApprovalTimelineActivity;

import java.util.ArrayList;


public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Plan> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, geotag, status, level, sector;
        TextView approvedBtn, rejectBtn;
        CardView parentLinear;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            sector = (TextView) view.findViewById(R.id.sector);
            geotag = (TextView) view.findViewById(R.id.geotag);
            status = (TextView) view.findViewById(R.id.status);
            level = (TextView) view.findViewById(R.id.level);
            approvedBtn = (TextView) view.findViewById(R.id.approvedBtn);
            rejectBtn = (TextView) view.findViewById(R.id.rejectBtn);
            parentLinear = (CardView) view.findViewById(R.id.parentLinear);
        }
    }


    public PlanAdapter(Context mainActivityUser, ArrayList<Plan> moviesList) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;

    }

    public void notifyData(ArrayList<Plan> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plan_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Plan mainFGD = moviesList.get(position);
        holder.name.setText(mainFGD.getActivity());
        holder.geotag.setText(mainFGD.getGeotag());
        holder.sector.setText(AppConfig.capitailizeWord(mainFGD.getSector()));

        if (mainFGD.status != null) {
            holder.status.setText(AppConfig.capitailizeWord(mainFGD.getStatus()));
        }
        if (mainFGD.status != null) {
            holder.level.setText(AppConfig.capitailizeWord(mainFGD.getLevel()));
        }

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivityUser, ApprovalTimelineActivity.class);
                mainActivityUser.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return String.valueOf((double) tmp / factor);
    }

}
