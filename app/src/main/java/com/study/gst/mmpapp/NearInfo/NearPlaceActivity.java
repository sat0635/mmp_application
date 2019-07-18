package com.study.gst.mmpapp.NearInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.study.gst.mmpapp.PersonInfo.MissionPlace;
import com.study.gst.mmpapp.PersonInfo.MyPage;
import com.study.gst.mmpapp.PersonInfo.Ranking;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.NetworkService;
import com.study.gst.mmpapp.model.Tour;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NearPlaceActivity extends Activity
        implements NavigationView.OnNavigationItemSelectedListener{

    private NetworkService networkService;
    private static NearPlaceActivity instance;
    public static NearPlaceActivity getInstance(){return instance;}
    private NearPlaceAdapter adapter = new NearPlaceAdapter();
    private Retrofit retrofit;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_place);
        textView = (TextView)findViewById(R.id.textview);

        init();
        NetworkService service = retrofit.create(NetworkService.class);

        Call<Tour> call = service.get_version();

        call.enqueue(new Callback<Tour>() {
            @Override
            public void onResponse(Call<Tour> call, Response<Tour> response) {
                Tour tour = response.body();

                Log.d("tag","lopal"+tour.version);

            }

            @Override
            public void onFailure(Call<Tour> call, Throwable t) {
                Log.d("tag","lopal fail");

            }
        });



        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void init() {
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl("http://106.10.35.40:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_home) {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }else if (id == R.id.nav_myPage) {


            Intent intent_act = new Intent(getApplicationContext(), MyPage.class);
            startActivity(intent_act);
            return true;

        } else if (id == R.id.nav_missionPlace) {

            Intent intent_act = new Intent(getApplicationContext(), MissionPlace.class);
            startActivity(intent_act);
            return true;

        } else if (id == R.id.nav_ranking) {

            Intent intent_act = new Intent(getApplicationContext(), Ranking.class);
            startActivity(intent_act);
            return true;

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        return true;
    }
}
