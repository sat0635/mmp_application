package com.study.gst.mmpapp.PersonInfo;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.study.gst.mmpapp.HTTP.HTTPCommunication;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.model.Tour;

import org.json.JSONArray;
import org.json.JSONException;

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

        new JSONTask().execute("http://172.30.1.9:8080/get/tourlist");
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
            Glide.with(MissionPlace.this).load( items.get(position).getImageURL()).into(imageView);

            TextView textView = (TextView)v.findViewById(R.id.textView);
            textView.setText(items.get(position).getName());


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MissionPlace.this, MissionPlacePopup.class);
                    intent.putExtra("INFO", items.get(position));

                    startActivity(intent);
                }
            });
            return v;
        }
    }




    public class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            return HTTPCommunication.getHTTP(urls[0]);
        }

        //doInBackground메소드가 끝나면 여기로 와서 텍스트뷰의 값을 바꿔준다.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("result", result);
            ArrayList<Tour> tourList = new ArrayList<Tour>();
            try {
                JSONArray tourListJson = new JSONArray(result);

                for(int i = 0; i < tourListJson.length(); i++) {
                    tourList.add(new Tour(tourListJson.getJSONObject(i)));
                }

                updateList(tourList);
            } catch (JSONException je) {
                je.printStackTrace();
            }



        }

    }


}