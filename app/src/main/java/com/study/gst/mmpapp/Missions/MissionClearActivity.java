package com.study.gst.mmpapp.Missions;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import com.study.gst.mmpapp.R;

public class MissionClearActivity extends ActivityGroup {

    private EditText textView;
    private TabHost tabHost;
    private EditText answer;
    private Button next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_clear);

        setTitle("");
        tabHost = (TabHost)findViewById(R.id.tabhost1);
        tabHost.setup(getLocalActivityManager());
        textView = (EditText) findViewById(R.id.text1);
        next_btn = (Button) findViewById(R.id.missionclearactivity_button_next);


        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}
