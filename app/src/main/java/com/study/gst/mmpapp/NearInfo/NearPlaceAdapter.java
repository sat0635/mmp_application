package com.study.gst.mmpapp.NearInfo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.study.gst.mmpapp.R;

import java.util.ArrayList;

public class NearPlaceAdapter extends RecyclerView.Adapter<NearPlaceAdapter.ViewHolder> {

    private ArrayList<NearPlaceImage> items = new ArrayList<>();

    @NonNull
    @Override
    public NearPlaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_place_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NearPlaceAdapter.ViewHolder viewHolder, int position) {

        NearPlaceImage item = items.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getUrl())
                .apply(new RequestOptions().circleCrop())
                .into(viewHolder.ivImage);
        viewHolder.tvBigPlaceName.setText(item.getBig_place());
        viewHolder.tvPlaceName.setText(item.getPlace());
        viewHolder.tvPlaceKm.setText(item.getKm());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<NearPlaceImage> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvPlaceName, tvPlaceKm, tvBigPlaceName;

        ViewHolder(View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.near_place_list_place_image);
            tvBigPlaceName = itemView.findViewById(R.id.near_place_list_place_big_name);
            tvPlaceKm = itemView.findViewById(R.id.near_place_list_place_km);
            tvPlaceName = itemView.findViewById(R.id.near_place_list_place_name);
        }
    }
}