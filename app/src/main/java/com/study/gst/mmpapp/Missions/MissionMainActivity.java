package com.study.gst.mmpapp.Missions;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.study.gst.mmpapp.R;

public class MissionMainActivity extends AppCompatActivity {
    Button okBtn, canclebtn;
    private int id;
    private int[] mission_pattern = new int[10];
    private int[] order_mission = new int[10];
    private int index=0;
    public static Context CONTEXT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_main);
        CONTEXT=this;
        okBtn = (Button)findViewById(R.id.button1);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(4){
                    case 1: // 타자왕 미션
                        Intent intent1 = new Intent(MissionMainActivity.this, TypingMissionActivity.class);
                        startActivityForResult(intent1, 1);
                        break;
                    case 2: // 벽화 미션
                      //  Intent intent2 = new Intent(MissionMainActivity.this,  WallMissionActivity.class);
                       // startActivityForResult(intent2, 1);
                        break;
                    case 3: // 인증샷 미션
                        Intent intent3 = new Intent(MissionMainActivity.this,  PhotoMissionActivity.class);
                        startActivityForResult(intent3, 1);
                        break;
                    case 4: // 퀴즈 미션
                        Intent intent4 = new Intent(MissionMainActivity.this,  QuizeActivity.class);
                        startActivityForResult(intent4, 1);
                        break;
                    case 5: // 숨은 그림찾기
                        Intent intent5 = new Intent(MissionMainActivity.this,  FindImageActivity.class);
                        startActivityForResult(intent5, 1);
                        break;
                    case 6: // 숨은 QR 찾기 미션
                        Intent intent6 = new Intent(MissionMainActivity.this,  FindQRcodeActivity.class);
                        startActivityForResult(intent6, 1);
                        break;

                }
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        return;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
