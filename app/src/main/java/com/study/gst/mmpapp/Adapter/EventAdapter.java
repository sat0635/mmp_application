package com.study.gst.mmpapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.Event;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private ArrayList<Event> items = new ArrayList<>();

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        EventAdapter.ViewHolder viewHolder = new EventAdapter.ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder viewHolder, int position) {

        Event item = items.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIMAGE())
                .into(viewHolder.ivEvent);


        viewHolder.tvTitle.setText(item.getTITLE());
        viewHolder.tvContent.setText(item.getCONTENT());
        viewHolder.tvGenre.setText("");

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Event> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivEvent;
        TextView tvTitle, tvContent, tvGenre;

        ViewHolder(View itemView) {
            super(itemView);

            ivEvent = itemView.findViewById(R.id.iv_item_picture);

            tvTitle = itemView.findViewById(R.id.tv_item_picture_title);
            tvContent = itemView.findViewById(R.id.tv_item_picture_content);
            tvGenre = itemView.findViewById(R.id.tv_item_picture_genre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("tag","lopal EventAdapter: click image");
                }
            });
        }
    }
}