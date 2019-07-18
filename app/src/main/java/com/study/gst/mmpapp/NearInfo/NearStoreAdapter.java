package com.study.gst.mmpapp.NearInfo;

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

import java.util.ArrayList;

public class NearStoreAdapter extends RecyclerView.Adapter<NearStoreAdapter.ViewHolder> {

    private ArrayList<NearStoreImage> items = new ArrayList<>();

    @NonNull
    @Override
    public NearStoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_place_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NearStoreAdapter.ViewHolder viewHolder, int position) {

        NearStoreImage item = items.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getUrl())
                .apply(new RequestOptions().circleCrop())
                .into(viewHolder.ivImage);
        viewHolder.tvBigPlaceName.setText(item.getBig_place());
        Log.d("tag","lopalstore"+item.getBig_place());
        viewHolder.tvPlaceName.setText(item.getPlace());
        viewHolder.tvPlaceKm.setText(item.getKm());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<NearStoreImage> items)
    {
        Log.d("tag","lopalplace what the fuck ttibak"+items.get(0));

        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvPlaceName, tvPlaceKm, tvBigPlaceName;

        ViewHolder(View itemView) {
            super(itemView);
            Log.d("tag","lopalstore what the fuck52523523");
            ivImage = itemView.findViewById(R.id.near_place_list_place_image);
            tvBigPlaceName = itemView.findViewById(R.id.near_place_list_place_big_name);
            tvPlaceKm = itemView.findViewById(R.id.near_place_list_place_km);
            tvPlaceName = itemView.findViewById(R.id.near_place_list_place_name);
        }
    }
}