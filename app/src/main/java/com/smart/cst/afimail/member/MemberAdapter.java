package com.smart.cst.afimail.member;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smart.cst.afimail.R;

import java.util.ArrayList;

/**
 * Created by jmpriyanka on 01-12-2018.
 */

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Member> memberArrayList;
    private MemberClick onFGDItemClick;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, sex, count;
        LinearLayout parentLinear;

        public MyViewHolder(View view) {
            super((view));
            name = (TextView) view.findViewById(R.id.name);
            sex = (TextView) view.findViewById(R.id.sex);
            count = (TextView) view.findViewById(R.id.count);
            parentLinear = (LinearLayout) view.findViewById(R.id.parentLinear);
        }
    }

    public MemberAdapter(Context mainActivityUser, ArrayList<Member> avgwageList, MemberClick onItemClick) {
        this.mainActivityUser = mainActivityUser;
        this.memberArrayList = avgwageList;
        this.onFGDItemClick = onItemClick;
    }

    public void notifyData(ArrayList<Member> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.memberArrayList = myList;
        notifyDataSetChanged();
    }

    public MemberAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memberow, parent, false);

        return new MemberAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MemberAdapter.MyViewHolder holder, final int position) {
        Member bean = memberArrayList.get(position);
        holder.name.setText(bean.memberName);
        holder.sex.setText(bean.sex);
        holder.count.setText(String.valueOf(position + 1));

        holder.parentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFGDItemClick.onMemberItemClick(position);
            }
        });
    }

    public int getItemCount() {
        return memberArrayList.size();
    }

}
