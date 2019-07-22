package com.study.gst.mmpapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkService{
    @GET("api/nearplace/{gpsx}/{gpsy}/")
    Call<List<Tour>> get_version(
            @Path("gpsx") Double gpsx,
            @Path("gpsy") Double gpsy
    );

    @GET("api/nearstore/{gpsx}/{gpsy}/")
    Call<List<Store>> get_version2(
            @Path("gpsx") Double gpsx,
            @Path("gpsy") Double gpsy
    );

    @GET("api/coupon/{userid}")
    Call<List<Coupon>> get_version3(
            @Path("userid") String userid
    );
    @GET("api/picture/")
    Call<List<Picture>> get_picture(
    );
}
