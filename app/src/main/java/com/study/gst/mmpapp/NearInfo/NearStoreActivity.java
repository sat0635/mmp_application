package com.study.gst.mmpapp.NearInfo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.study.gst.mmpapp.Adapter.NearStoreAdapter;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.GpsTracker;
import com.study.gst.mmpapp.model.NetworkService;
import com.study.gst.mmpapp.model.Store;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NearStoreActivity extends AppCompatActivity{

    private NearStoreAdapter adapter = new NearStoreAdapter();
    private Retrofit retrofit;
    private ArrayList<Store> items = new ArrayList<>();

    private GpsTracker gpsTracker;

    private double longitude;
    private double latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_store);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        gpsTracker = new GpsTracker(NearStoreActivity.this);

        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        Log.d("tag","lopal gpsx"+latitude);

        new JSONTask().execute();

    }


    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<List<Store>> call = service.get_version2(latitude,longitude);

            call.enqueue(new Callback<List<Store>>() {

                @Override
                public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                    List<Store> stores = response.body();
                    for (Store store : stores) {
                        items.add(store);
                    }
                    Log.d("tag", "lopal: onResponse");

                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(NearStoreActivity.this, LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);

                    adapter.setItems(items);
                }

                @Override
                public void onFailure(Call<List<Store>> call, Throwable t) {
                    Log.d("tag", "lopal fail");
                }
            });
            return "done";
        }

        //doInBackground메소드가 끝나면 여기로 와서 텍스트뷰의 값을 바꿔준다.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("result", "lopal onpostExecute");



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
