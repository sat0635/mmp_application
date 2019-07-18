package com.study.gst.mmpapp.NearInfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.study.gst.mmpapp.model.Tour;

import java.util.ArrayList;


public class NearPlaceGalleryData extends AppCompatActivity {
    private String URL="http://106.10.35.40:8000/api/nearplace/35.844847/128.466193/";

    ArrayList<Tour> items = new ArrayList<Tour>();





    public ArrayList<Tour> getItems() {



        Log.d("tag","after lopal execute");
        return items;
    }

}
