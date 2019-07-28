package com.study.gst.mmpapp.SNS;

import android.content.Intent;
import android.net.Uri;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.study.gst.mmpapp.Adapter.CommentAdapter;
import com.study.gst.mmpapp.model.Comment;
import com.study.gst.mmpapp.NearInfo.NearPlaceActivity;
import com.study.gst.mmpapp.PersonInfo.Ranking;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.RESTAPI.NetworkService;
import com.study.gst.mmpapp.config;
import com.study.gst.mmpapp.model.Comment;
import com.study.gst.mmpapp.model.Comment;
import com.study.gst.mmpapp.model.Heart;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class CommentActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private Retrofit retrofit;
    private ArrayList<Comment> items = new ArrayList<>();
    private CommentAdapter adapter = new CommentAdapter();
    private int ID;
    private String URL;
    private ImageView imageview;
    private ImageView heart;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent = getIntent();
        ID = intent.getIntExtra("ID",0);
        URL = intent.getStringExtra("IMAGEURL");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //왼쪽의 메뉴탭 반응
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                             @Override
                                                             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int id = item.getItemId();
                 if (id == R.id.nav_home) {
                     DrawerLayout drawer = findViewById(R.id.drawer_layout);
                     drawer.closeDrawer(GravityCompat.START);
                     return true;
                     //인증샷 갤러리
                 }else if (id == R.id.nav_myPage) {
                     Intent intent_act = new Intent(getApplicationContext(), SNSActivity.class);
                     startActivity(intent_act);
                     return true;
                 } else if (id == R.id.nav_missionPlace) {
                     Intent intent_act = new Intent(getApplicationContext(), NearPlaceActivity.class);
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
        );

        imageview = (ImageView) findViewById(R.id.activity_comment_picture);
        Glide.with(this)
                .load(URL)
                .apply(new RequestOptions().centerCrop())
                .into(imageview);
        new CommentJSONTask().execute();

        heart=(ImageView) findViewById(R.id.activity_comment_heart);
        new GetHeartJSONTask().execute();
        textView = (TextView) findViewById(R.id.activity_comment_heart_number);
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HeartJSONTask().execute();
                new GetHeartJSONTask().execute();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    public class CommentJSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<List<Comment>> call = service.get_comment(ID);

            call.enqueue(new Callback<List<Comment>>() {

                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    List<Comment> pictures = response.body();
                    for (Comment picture : pictures) {
                        items.add(picture);
                    }
                    Log.d("tag", "lopal: onResponse");

                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this, LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(adapter);

                    adapter.setItems(items);
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {
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
    public class HeartJSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<Heart> call = service.send_heart(ID);

            call.enqueue(new Callback<Heart>() {

                @Override
                public void onResponse(Call<Heart> call, Response<Heart> response) {
                    Heart pictures = response.body();

                }

                @Override
                public void onFailure(Call<Heart> call, Throwable t) {
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

    public class GetHeartJSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            init();
            NetworkService service = retrofit.create(NetworkService.class);
            Call<Heart> call = service.get_heart(ID);

            call.enqueue(new Callback<Heart>() {

                @Override
                public void onResponse(Call<Heart> call, Response<Heart> response) {
                    Heart heart = response.body();


                    textView.setText(String.valueOf(heart.getNUMBER()));


                }

                @Override
                public void onFailure(Call<Heart> call, Throwable t) {
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
                .baseUrl(config.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
