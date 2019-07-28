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
import com.bumptech.glide.request.RequestOptions;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.Comment;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private ArrayList<Comment> items = new ArrayList<>();


    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_place_list, parent, false);
        CommentAdapter.ViewHolder viewHolder = new CommentAdapter.ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder viewHolder, int position) {

        Comment item = items.get(position);
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIMAGE())
                .apply(new RequestOptions().centerCrop())
                .into(viewHolder.ivImage);
        viewHolder.tvBigPlaceName.setText(item.getTITLE());
        viewHolder.tvPlaceName.setText("");
        viewHolder.tvPlaceKm.setText(item.getCONTENT());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Comment> items) {

        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvPlaceName, tvPlaceKm, tvBigPlaceName;

        ViewHolder(View itemView) {
            super(itemView);

            Log.d("tag","lopal : CommentApapter viewHolder");
            ivImage = itemView.findViewById(R.id.near_place_list_place_image);
            tvBigPlaceName = itemView.findViewById(R.id.near_place_list_place_big_name);
            tvPlaceKm = itemView.findViewById(R.id.near_place_list_place_km);
            tvPlaceName = itemView.findViewById(R.id.near_place_list_place_name);
        }
    }
}
