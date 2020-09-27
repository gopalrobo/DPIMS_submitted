package com.smart.cst.afimail.village;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.cst.afimail.R;
import com.smart.cst.afimail.member.MemberClick;

import java.util.ArrayList;

/**
 * Created by jmpriyanka on 01-12-2018.
 */

public class VillageAdapter extends RecyclerView.Adapter<VillageAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Village> memberArrayList;
    private MemberClick onFGDItemClick;



    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name,numberOfHouseholds,count;
        LinearLayout parentLinear;

        public MyViewHolder(View view){
            super((view));
            name =(TextView) view.findViewById(R.id.name);
            count = (TextView) view.findViewById(R.id.count);
            numberOfHouseholds =(TextView) view.findViewById(R.id.numberOfHouseholds);
            parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);
        }
    }

    public VillageAdapter(Context mainActivityUser, ArrayList<Village> avgwageList, MemberClick onItemClick) {
        this.mainActivityUser = mainActivityUser;
        this.memberArrayList = avgwageList;
        this.onFGDItemClick =  onItemClick;
    }

    public void notifyData(ArrayList<Village> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.memberArrayList = myList;
        notifyDataSetChanged();
    }
    public VillageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.villagerow, parent, false);

        return new VillageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VillageAdapter.MyViewHolder holder, final int position) {
        Village bean = memberArrayList.get(position);
        holder.name.setText(bean.nameVillagesHamlets);
        holder.numberOfHouseholds.setText(bean.numberOfHouseholds);
        holder.count.setText(String.valueOf(position + 1));


        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFGDItemClick.onMemberItemClick(position);
            }
        });
    }
    public  int getItemCount(){
        return memberArrayList.size();
    }

}
