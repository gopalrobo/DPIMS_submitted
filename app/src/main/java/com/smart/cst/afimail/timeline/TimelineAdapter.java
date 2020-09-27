package com.smart.cst.afimail.timeline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.repsly.library.timelineview.LineType;
import com.repsly.library.timelineview.TimelineView;
import com.smart.cst.afimail.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Adapter for RecyclerView with TimelineView
 */

class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private int orientation;
    private ArrayList<Timeline> items;
    private Context context;
    TimelineListener timelineListener;
    private static DecimalFormat df1 = new DecimalFormat("0.00");

    TimelineAdapter(int orientation, ArrayList<Timeline> items, Context context, TimelineListener timelineListener) {
        this.orientation = orientation;
        this.items = items;
        this.context = context;
        this.timelineListener = timelineListener;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.approval_time_vertical;

    }

    public void notifiData(ArrayList<Timeline> listItems) {

        items = listItems;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvStatus.setText(items.get(position).getStatus());
        holder.timelineView.setLineType(getLineType(position));
        if (position == 0) {
            holder.timelineView.setNumber("P");
        }else if (position == 21) {
            holder.timelineView.setNumber("IM");
        } else if (position <= 21) {
            holder.timelineView.setNumber(String.valueOf(position ));
        }else{
            holder.timelineView.setNumber(String.valueOf(position -1));
        }
        holder.timelineView.setFillMarker(false);

        if (position == 0 || position == 1 || position == 2) {
            holder.timelineView.setFillMarker(true);
            holder.timelineView.setMarkerColor(context.getResources().getColor(R.color.greenafi));
            holder.timelineView.setEndLineColor(context.getResources().getColor(R.color.greenafi));
            holder.timelineView.setStartLineColor(context.getResources().getColor(R.color.greenafi));
            holder.timelineView.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.timelineView.setDrawable(null);
            holder.timelineView.setFillMarker(false);
            holder.timelineView.setMarkerColor(context.getResources().getColor(R.color.colorPrimary));
            holder.timelineView.setEndLineColor(context.getResources().getColor(R.color.colorPrimary));
            holder.timelineView.setStartLineColor(context.getResources().getColor(R.color.colorPrimary));
            holder.timelineView.setTextColor(context.getResources().getColor(R.color.black));

        }
        if (items.get(position).getStatus().length() == 0) {
            holder.textviewLayout.setVisibility(View.VISIBLE);
            holder.contentLayout.setVisibility(View.GONE);
            holder.textview.setText("PLANNING");
            if (position == 21) {
                holder.textview.setText("IMPLEMENTATION & MONITORING");
            }
        } else {
            holder.textviewLayout.setVisibility(View.GONE);
            holder.textview.setText("");
            holder.contentLayout.setVisibility(View.VISIBLE);
        }


        // Set every third item active
        if (position == 3) {
            holder.timelineView.setActive(true);
        } else {
            holder.timelineView.setActive(false);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private LineType getLineType(int position) {
        if (getItemCount() == 1) {
            return LineType.ONLYONE;

        } else {
            if (position == 0) {
                return LineType.BEGIN;

            } else if (position == getItemCount() - 1) {
                return LineType.END;

            } else {
                return LineType.NORMAL;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TimelineView timelineView;
        LinearLayout textviewLayout, contentLayout;
        TextView tvStatus, textview;

        ViewHolder(View view) {
            super(view);
            timelineView = (TimelineView) view.findViewById(R.id.timeline);
            tvStatus = (TextView) view.findViewById(R.id.tv_status);
            textviewLayout = (LinearLayout) view.findViewById(R.id.textviewLayout);
            contentLayout = (LinearLayout) view.findViewById(R.id.contentLayout);
            textview = (TextView) view.findViewById(R.id.textview);
        }
    }


}
