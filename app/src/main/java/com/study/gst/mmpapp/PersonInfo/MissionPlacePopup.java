package com.study.gst.mmpapp.PersonInfo;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.Tour;

//팝업창으로 해당 미션장소 상세한 정보 띄우기
public class MissionPlacePopup  extends ActivityGroup {

    TextView textView;
    TextView textView2;
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tour tour = (Tour)getIntent().getParcelableExtra("INFO");

        setContentView(R.layout.mission_place_popup);
        setTitle("미션장소");
        tabHost = (TabHost)findViewById(R.id.tabhost1);
        tabHost.setup(getLocalActivityManager());
        TabHost.TabSpec tab1 = tabHost.newTabSpec("A").setContent(R.id.tabPop).setIndicator("미션목록");
        textView = (TextView) findViewById(R.id.tabText1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("B").setContent(R.id.tabPop2).setIndicator("장소정보");
        textView = (TextView) findViewById(R.id.tabText2);

        tabHost.addTab(tab2);


    }

}
