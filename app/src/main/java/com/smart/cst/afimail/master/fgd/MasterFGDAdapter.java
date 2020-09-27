package com.smart.cst.afimail.master.fgd;

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
import com.smart.cst.afimail.staff.Farmer;

import java.util.ArrayList;


public class MasterFGDAdapter extends RecyclerView.Adapter<MasterFGDAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<FGDBean> moviesList;
    int row_index = -1;
    MasterClick reportClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question,
                questionType,
                section;
        CardView parentLinear;


        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question);
            questionType = (TextView) view.findViewById(R.id.questionType);
            section = (TextView) view.findViewById(R.id.section);

            parentLinear = (CardView) view.findViewById(R.id.parentLinear);
        }
    }


    public MasterFGDAdapter(Context mainActivityUser, ArrayList<FGDBean> moviesList, MasterClick reportClick) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;
        this.reportClick = reportClick;

    }

    public void notifyData(ArrayList<FGDBean> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.master_fgd_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        FGDBean bean = moviesList.get(position);
        holder.question.setText(bean.getQuestion());
        holder.questionType.setText(bean.getQuestionType());
        holder.section.setText(bean.getSection());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
