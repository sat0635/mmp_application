package com.study.gst.mmpapp.Intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

import com.study.gst.mmpapp.NearInfo.NearStoreActivity;
import com.study.gst.mmpapp.NearInfo.NearStoreAdapter;
import com.study.gst.mmpapp.PersonInfo.MissionPlace;
import com.study.gst.mmpapp.PersonInfo.MyPage;
import com.study.gst.mmpapp.NearInfo.NearPlaceActivity;
import com.study.gst.mmpapp.QRcodeActivity;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.PersonInfo.Ranking;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private PictureAdapter adapter = new PictureAdapter();
    private Button mission_button;
    private Button place_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);



        mission_button = (Button)findViewById(R.id.mission_button);
        place_button = (Button)findViewById(R.id.place_button);

        mission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QRcodeActivity.class);
                startActivity(intent);
            }
        });

        place_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NearPlaceActivity.class);
                startActivity(intent);
            }
        });

        //recycleView 초기화, 메인페이지에서 인스타느낌의 사진 스크롤뷰를 나타내기 위함
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //아이템 로드
        adapter.setItems(new GalleryData().getItems());

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.mainactivity_bottomnavigationview);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_store:
                        Intent intent1 = new Intent(MainActivity.this, NearStoreActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.action_account:
                        //getFragmentManager().beginTransaction().replace(R.id.mainactivity_framelayout, new AccountFragment()).commit();
                        Intent intent2 = new Intent(MainActivity.this, MyPage.class);
                        startActivity(intent2);

                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
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
