package com.study.gst.mmpapp.PersonInfo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.study.gst.mmpapp.HTTP.HTTPCommunication;
import com.study.gst.mmpapp.NearInfo.NearPlaceActivity;
import com.study.gst.mmpapp.NearInfo.NearPlaceAdapter;
import com.study.gst.mmpapp.NearInfo.NearStoreActivity;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.Coupon;
import com.study.gst.mmpapp.model.NetworkService;
import com.study.gst.mmpapp.model.Tour;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponActivity extends AppCompatActivity {

    private CouponAdapter adapter = new CouponAdapter();
    private Retrofit retrofit;
    private ArrayList<Coupon> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);


        new JSONTask().execute();

    }
    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<List<Coupon>> call = service.get_version3("20171105" );

            call.enqueue(new Callback<List<Coupon>>() {

                @Override
                public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                    List<Coupon> coupons = response.body();
                    for (Coupon coupon : coupons) {
                        items.add(coupon);
                    }
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CouponActivity.this, LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);

                    adapter.setItems(items);
                }

                @Override
                public void onFailure(Call<List<Coupon>> call, Throwable t) {
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
