package com.study.gst.mmpapp.RESTAPI;

import com.study.gst.mmpapp.model.Comment;
import com.study.gst.mmpapp.model.Coupon;
import com.study.gst.mmpapp.model.Event;
import com.study.gst.mmpapp.model.Heart;
import com.study.gst.mmpapp.model.Picture;
import com.study.gst.mmpapp.model.Store;
import com.study.gst.mmpapp.model.Tour;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//REST API
public interface NetworkService{
    //근처 관광지 가져오기
    @GET("api/nearplace/{gpsx}/{gpsy}/")
    Call<List<Tour>> get_version(
            @Path("gpsx") Double gpsx,
            @Path("gpsy") Double gpsy
    );
    //근처 가맹점 가져오기
    @GET("api/nearstore/{gpsx}/{gpsy}/")
    Call<List<Store>> get_version2(
            @Path("gpsx") Double gpsx,
            @Path("gpsy") Double gpsy
    );
    //자신의 쿠폰 가져오기
    @GET("api/coupon/{userid}/")
    Call<List<Coupon>> get_version3(
            @Path("userid") String userid
    );
    //모든 인증샷 가져오기
    @GET("api/picture/")
    Call<List<Picture>> get_picture(
    );
    //모든 이벤트 가져오기
    @GET("api/event/")
    Call<List<Event>> get_event(
    );
    //신규 유저 등록하기
    @GET("api/insertuser/{userid}/")
    Call<Event> insert_user(
            @Path("userid") String userid
    );
    @GET("api/comment/{pictureid}/")
    Call<List<Comment>> get_comment(
            @Path("pictureid") int pictureid
    );
    @GET("api/heart/{pictureid}/")
    Call<Heart> send_heart(
            @Path("pictureid") int pictureid
    );
    @GET("api/getheart/{pictureid}/")
    Call<Heart> get_heart(
            @Path("pictureid") int pictureid
    );
}
