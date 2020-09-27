package com.smart.cst.afimail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MasterAllFGDSurveyAdapter extends RecyclerView.Adapter<MasterAllFGDSurveyAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<FGDNewBean> moviesList;
    SharedPreferences sharedpreferences;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name,tdate,location,date;
        ImageView editImage, locationImage;
        LinearLayout parentLinear;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            tdate = (TextView) view.findViewById(R.id.tdate);
            location = (TextView) view.findViewById(R.id.location);
            date = (TextView) view.findViewById(R.id.date);
            parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);
        }
    }



    public MasterAllFGDSurveyAdapter(Context mainActivityUser, ArrayList<FGDNewBean> moviesList) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;

    }

    public void notifyData(ArrayList<FGDNewBean> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.survey_list_fgd_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        FGDNewBean mainFGD = moviesList.get(position);
        holder.name.setText(mainFGD.getVillage());
        holder.tdate.setText(mainFGD.getGramPanchayat());
        holder.location.setText(mainFGD.getMandal());
        holder.date.setText(mainFGD.getDateOfFGD());

        if (position % 2 == 0) {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.viewBg));
        } else {
            holder.parentLinear.setBackgroundColor(mainActivityUser.getResources().getColor(R.color.white));
        }

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivityUser, MainActivityNewFGD.class);
                intent.putExtra("object", moviesList.get(position));
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
