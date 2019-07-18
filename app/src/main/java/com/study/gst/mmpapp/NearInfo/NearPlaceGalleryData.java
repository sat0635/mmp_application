package com.study.gst.mmpapp.NearInfo;

import android.util.Log;

import com.study.gst.mmpapp.model.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearPlaceGalleryData {

    ArrayList<Tour> items = new ArrayList<>();
    private boolean flag=false;
    private Retrofit retrofit;
    public ArrayList<Tour> getItems() throws InterruptedException {



        init();
        NetworkService service = retrofit.create(NetworkService.class);
        Call<List<Tour>> call = service.get_version();

        call.enqueue(new Callback<List<Tour>>() {
            @Override
            public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                List<Tour> tours = response.body();
                for ( Tour tour : tours){
                    items.add(tour);
                    Log.d("tag","lopal"+tour.getIMAGENAME());
                }
                Log.d("tag","lopal check1");
                flag=true;
            }

            @Override
            public void onFailure(Call<List<Tour>> call, Throwable t) {
                Log.d("tag","lopal fail");
            }
        });

        Log.d("tag","lopal done");
        while (true) {
            if (flag==true) {
                return items;
            } else {
                Thread.sleep(1000);
                continue;
            }
        }
    }
    public void init() {
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl("http://106.10.35.40:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
