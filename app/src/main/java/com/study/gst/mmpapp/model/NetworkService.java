package com.study.gst.mmpapp.model;

import com.study.gst.mmpapp.NearInfo.Tour;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkService{
    @GET("api/")
    Call<List<Tour>> get_version();
}
