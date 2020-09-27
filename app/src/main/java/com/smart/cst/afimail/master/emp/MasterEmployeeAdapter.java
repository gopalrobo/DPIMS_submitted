package com.smart.cst.afimail.master.emp;

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
import com.smart.cst.afimail.master.province.Province;

import java.util.ArrayList;


public class MasterEmployeeAdapter extends RecyclerView.Adapter<MasterEmployeeAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Employee> moviesList;
    int row_index = -1;
    MasterClick reportClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fullName,
                identification,
                mobile,
                email;
        CardView parentLinear;


        public MyViewHolder(View view) {
            super(view);
            fullName = (TextView) view.findViewById(R.id.fullname);
            identification = (TextView) view.findViewById(R.id.identification);
            mobile = (TextView) view.findViewById(R.id.mobile);
            email = (TextView) view.findViewById(R.id.email);

            parentLinear = (CardView) view.findViewById(R.id.parentLinear);
        }
    }


    public MasterEmployeeAdapter(Context mainActivityUser, ArrayList<Employee> moviesList, MasterClick reportClick) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;
        this.reportClick = reportClick;

    }

    public void notifyData(ArrayList<Employee> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.master_employee_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Employee bean = moviesList.get(position);
        holder.fullName.setText(bean.getFullName());
        holder.identification.setText(bean.getEmpId());
        holder.mobile.setText(bean.getMobile());
        holder.email.setText(bean.getEmail());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
