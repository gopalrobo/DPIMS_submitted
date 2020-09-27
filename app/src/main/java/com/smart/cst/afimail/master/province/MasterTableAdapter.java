package com.smart.cst.afimail.master.province;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart.cst.afimail.R;
import com.smart.cst.afimail.master.MasterClick;

import java.util.ArrayList;


public class MasterTableAdapter extends RecyclerView.Adapter<MasterTableAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Province> moviesList;
    int row_index = -1;
    MasterClick reportClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,
                code,
                dari,
                pashtu;
        CardView parentLinear;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            code = (TextView) view.findViewById(R.id.code);
            dari = (TextView) view.findViewById(R.id.dari);
            pashtu = (TextView) view.findViewById(R.id.pashtu);

            parentLinear = (CardView) view.findViewById(R.id.parentLinear);
        }
    }


    public MasterTableAdapter(Context mainActivityUser, ArrayList<Province> moviesList, MasterClick reportClick) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;
        this.reportClick = reportClick;

    }

    public void notifyData(ArrayList<Province> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.master_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Province bean = moviesList.get(position);
        holder.name.setText(bean.getProvinceName());
        holder.code.setText(bean.getProvinceCode());
        holder.dari.setText(bean.getProvinceNameDari());
        holder.pashtu.setText(bean.getProvinceNamePashtu());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
