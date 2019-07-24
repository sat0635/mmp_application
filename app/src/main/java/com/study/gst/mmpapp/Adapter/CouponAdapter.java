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
import com.study.gst.mmpapp.model.Coupon;

import java.util.ArrayList;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder>{
    private ArrayList<Coupon> items = new ArrayList<>();


    @NonNull
    @Override
    public CouponAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_place_list, parent, false);
        CouponAdapter.ViewHolder viewHolder = new CouponAdapter.ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponAdapter.ViewHolder viewHolder, int position) {

        Coupon item = items.get(position);
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIMAGE())
                .apply(new RequestOptions().centerCrop())
                .into(viewHolder.ivImage);
        viewHolder.tvBigPlaceName.setText(item.getSTORENAME());
        viewHolder.tvPlaceName.setText(item.getNAME());
        viewHolder.tvPlaceKm.setText(Integer.toString(item.getCOUPONID()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Coupon> items) {

        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvPlaceName, tvPlaceKm, tvBigPlaceName;

        ViewHolder(View itemView) {
            super(itemView);

            Log.d("tag","lopal : CouponApapter viewHolder");
            ivImage = itemView.findViewById(R.id.near_place_list_place_image);
            tvBigPlaceName = itemView.findViewById(R.id.near_place_list_place_big_name);
            tvPlaceKm = itemView.findViewById(R.id.near_place_list_place_km);
            tvPlaceName = itemView.findViewById(R.id.near_place_list_place_name);
        }
    }
}
