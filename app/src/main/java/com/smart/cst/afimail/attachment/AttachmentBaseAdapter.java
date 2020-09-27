package com.smart.cst.afimail.attachment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smart.cst.afimail.R;
import com.smart.cst.afimail.app.GlideApp;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class AttachmentBaseAdapter extends RecyclerView.Adapter<AttachmentBaseAdapter.MyViewHolder> {

    private Context mainActivityUser;
    private ArrayList<Base> moviesList;
    private BaseClick attachmentClick;
    private int count;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView cancelImg, image;

        public MyViewHolder(View view) {
            super(view);
            image = (CircleImageView) view.findViewById(R.id.image);
            cancelImg = (CircleImageView) view.findViewById(R.id.cancelImg);

        }
    }


    public AttachmentBaseAdapter(Context mainActivityUser, ArrayList<Base> moviesList, BaseClick attachmentClick, int count) {
        this.moviesList = moviesList;
        this.mainActivityUser = mainActivityUser;
        this.attachmentClick = attachmentClick;
        this.count = count;

    }

    public void notifyData(ArrayList<Base> myList) {
        this.moviesList = myList;
        notifyDataSetChanged();
    }

    public void notifyDataItem(ArrayList<Base> myList, int position) {
        this.moviesList = myList;
        notifyItemChanged(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_attachment_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Base bean = moviesList.get(position);

        if (position == 0) {
            GlideApp.with(mainActivityUser).load(R.drawable.new_file)
                    .dontAnimate()
                    .thumbnail(0.5f)
                    .placeholder(R.drawable.file)
                    .into(holder.image);
            holder.cancelImg.setVisibility(View.GONE);
        } else {
            if( bean.url.startsWith("coconut")) {
                holder.cancelImg.setVisibility(View.VISIBLE);
                GlideApp.with(mainActivityUser).load( "http://" + bean.url)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.file)
                        .into(holder.image);
            }else {
                holder.cancelImg.setVisibility(View.VISIBLE);
                GlideApp.with(mainActivityUser).load( bean.url)
                        .dontAnimate()
                        .thumbnail(0.5f)
                        .placeholder(R.drawable.file)
                        .into(holder.image);
            }
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachmentClick.onBaseClick(position, count);
            }
        });
        holder.cancelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachmentClick.onDeleteClick(position, count);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
