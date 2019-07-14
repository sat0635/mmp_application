package com.study.gst.mmpapp.Missions;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import com.study.gst.mmpapp.R;

public class QuizeActivity extends ActivityGroup {

    private EditText textView;
    private TabHost tabHost;
    private EditText answer;
    private Button answer_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quize);
        setTitle("");
        tabHost = (TabHost)findViewById(R.id.tabhost1);
        tabHost.setup(getLocalActivityManager());
        TabHost.TabSpec tab1 = tabHost.newTabSpec("A").setContent(R.id.tabPop).setIndicator("정답을 입력하세요");
        textView = (EditText) findViewById(R.id.text1);
        tabHost.addTab(tab1);
        answer_btn = (Button) findViewById(R.id.quizeactivity_button_answer);

        answer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizeActivity.this, MissionClearActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
