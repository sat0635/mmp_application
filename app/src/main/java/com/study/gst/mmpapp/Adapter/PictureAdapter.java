package com.study.gst.mmpapp.Adapter;

import android.content.Intent;
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
import com.study.gst.mmpapp.SNS.CommentActivity;
import com.study.gst.mmpapp.SNS.SNSActivity;
import com.study.gst.mmpapp.model.Comment;
import com.study.gst.mmpapp.model.Picture;

import java.util.ArrayList;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private ArrayList<Picture> items = new ArrayList<>();
    private int ID;
    @NonNull
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PictureAdapter.ViewHolder viewHolder, int position) {

        Picture item = items.get(position);
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIMAGE())
                .into(viewHolder.ivPicture);

        viewHolder.tvTitle.setText(item.getTITLE());
        viewHolder.tvContent.setText(item.getCONTENT());
        viewHolder.tvGenre.setText(String.valueOf(item.getID()));
        viewHolder.tvURL.setText(item.getIMAGE());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Picture> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPicture;
        TextView tvTitle, tvContent, tvGenre, tvURL;

        ViewHolder(View itemView) {
            super(itemView);

            ivPicture = itemView.findViewById(R.id.iv_item_picture);
            tvGenre = itemView.findViewById(R.id.tv_item_picture_genre);
            tvURL=itemView.findViewById(R.id.tv_item_picture_url);
            ivPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), CommentActivity.class);
                    Log.d("tag","lopalAdapter:"+ tvGenre.getText());
                    String temp = (String) tvGenre.getText();
                    intent.putExtra("ID",Integer.parseInt(temp));
                    intent.putExtra("IMAGEURL",(String)tvURL.getText());
                    view.getContext().startActivity(intent);
                }
            });
            tvTitle = itemView.findViewById(R.id.tv_item_picture_title);
            tvContent = itemView.findViewById(R.id.tv_item_picture_content);

        }
    }
}