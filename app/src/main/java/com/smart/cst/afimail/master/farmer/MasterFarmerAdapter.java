package com.smart.cst.afimail.master.farmer;

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
import com.smart.cst.afimail.master.emp.Employee;
import com.smart.cst.afimail.staff.Farmer;

import java.util.ArrayList;


public class MasterFarmerAdapter extends RecyclerView.Adapter<MasterFarmerAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Farmer> moviesList;
    int row_index = -1;
    MasterClick reportClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fullName,
                village,
                mobile,
                district;
        CardView parentLinear;


        public MyViewHolder(View view) {
            super(view);
            fullName = (TextView) view.findViewById(R.id.fullname);
            village = (TextView) view.findViewById(R.id.village);
            mobile = (TextView) view.findViewById(R.id.mobile);
            district = (TextView) view.findViewById(R.id.district);

            parentLinear = (CardView) view.findViewById(R.id.parentLinear);
        }
    }


    public MasterFarmerAdapter(Context mainActivityUser, ArrayList<Farmer> moviesList, MasterClick reportClick) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;
        this.reportClick = reportClick;

    }

    public void notifyData(ArrayList<Farmer> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.master_staff_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Farmer bean = moviesList.get(position);
        holder.fullName.setText(bean.getName());
        holder.district.setText(bean.getDistrict());
        holder.mobile.setText(bean.getContact());
        holder.village.setText(bean.getVillage());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
