package com.smart.cst.afimail.master;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.cst.afimail.R;

import java.util.ArrayList;



public class MasterItemAdapter extends RecyclerView.Adapter<MasterItemAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<MasterItem> moviesList;
    int row_index = 1;
    MasterClick reportClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imgIcon;
        LinearLayout imgLin;


        public MyViewHolder(View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
            imgLin = (LinearLayout) view.findViewById(R.id.imgLin);
        }
    }


    public MasterItemAdapter(Context mainActivityUser, ArrayList<MasterItem> moviesList,
                             MasterClick reportClick) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;
        this.reportClick = reportClick;

    }

    public void notifyData(ArrayList<MasterItem> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.master_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        MasterItem bean = moviesList.get(position);
        holder.txtTitle.setText(bean.title);

        holder.imgIcon.setImageResource(bean.imgId);
        holder.imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
                reportClick.itemClick(position);
            }
        });
        if (row_index == position) {
            holder.imgLin.setBackground(
                    mainActivityUser.getResources().getDrawable(R.drawable.round_light));
        } else {
            holder.imgLin.setBackground(
                    mainActivityUser.getResources().getDrawable(R.drawable.round));
        }

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
