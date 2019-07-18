package com.study.gst.mmpapp.NearInfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.study.gst.mmpapp.HTTP.HTTPCommunication;
import com.study.gst.mmpapp.PersonInfo.MissionPlace;
import com.study.gst.mmpapp.PersonInfo.MyPage;
import com.study.gst.mmpapp.PersonInfo.Ranking;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.NetworkService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NearPlaceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private boolean flag=false;
    private NearPlaceAdapter adapter = new NearPlaceAdapter();
    private Retrofit retrofit;
    private ArrayList<Tour> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_place);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_near_place);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //왼쪽의 메뉴탭 반응
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        new JSONTask().execute("http://106.10.35.40:8000/api/nearplace/35.844847/128.466193/");


    }

    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<List<Tour>> call = service.get_version();

            call.enqueue(new Callback<List<Tour>>() {

                @Override
                public void onResponse(Call<List<Tour>> call, Response<List<Tour>> response) {
                    List<Tour> tours = response.body();
                    for (Tour tour : tours) {
                        items.add(tour);
                        Log.d("tag", "lopal" + tour.getIMAGENAME());
                    }
                    Log.d("tag", "lopal check1");
                    Log.d("tag", "lopal check2");

                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(NearPlaceActivity.this, LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);

                    adapter.setItems(items);
                }

                @Override
                public void onFailure(Call<List<Tour>> call, Throwable t) {
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
