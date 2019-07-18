package com.study.gst.mmpapp.PersonInfo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.NearInfo.Tour;

import java.util.ArrayList;


public class MissionPlace extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mission_place);

        listView = (ListView)this.findViewById(R.id.listView);

    }

    private void updateList(ArrayList<Tour> tourList) {
        CustomAdapter adapter = new CustomAdapter(this, 0, tourList);
        listView.setAdapter(adapter);
    }

    private class CustomAdapter extends ArrayAdapter<Tour> {
        private ArrayList<Tour> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<Tour> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.item_list, null);
            }

            // ImageView 인스턴스
            ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
            Button button = (Button)v.findViewById(R.id.button);
            // 리스트뷰의 아이템에 이미지를 변경한다.

            TextView textView = (TextView)v.findViewById(R.id.textView);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MissionPlace.this, MissionPlacePopup.class);

                    startActivity(intent);
                }
            });
            return v;
        }
    }

}