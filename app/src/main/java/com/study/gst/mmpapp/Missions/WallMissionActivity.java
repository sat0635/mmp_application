package com.study.gst.mmpapp.Missions;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.study.gst.mmpapp.PersonInfo.MissionPlace;
import com.study.gst.mmpapp.PersonInfo.MyPage;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.PersonInfo.Ranking;

public class WallMissionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button tip_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wall_mission);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_wall_mission);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //왼쪽의 메뉴탭 반응
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        tip_button = (Button)findViewById(R.id.tip_button);



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
