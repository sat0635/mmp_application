package com.study.gst.mmpapp.PersonInfo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.study.gst.mmpapp.HTTP.HTTPCommunication;
import com.study.gst.mmpapp.R;
import com.study.gst.mmpapp.NearInfo.Tour;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);


        listView = (ListView) this.findViewById(R.id.coupon_list);
        new JSONTask().execute("http://106.10.35.40:8000/api");


        ArrayList<String> items = new ArrayList<>();

        items.add("김광석거리");
        items.add("근대화거리");
        items.add("강정보");
        items.add("이월드");
        Log.d("check1","check1");
        CustomAdapter adapter = new CustomAdapter(this, 0, items);
        listView.setAdapter(adapter);
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
                    Log.d("tag","lopal"+tourListJson.getJSONObject(i));
                }

            } catch (JSONException je) {
                je.printStackTrace();
            }



        }

    }

    private class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.coupon_item, null);
            }

            // ImageView 인스턴스
            ImageView imageView = (ImageView)v.findViewById(R.id.coupon_item);

            // 리스트뷰의 아이템에 이미지를 변경한다.
            if("김광석거리".equals(items.get(position))){
                Glide.with(CouponActivity.this).load( "http://155.230.91.237:26002/coupon/coupon1.png").into(imageView);
            } else if("근대화거리".equals(items.get(position)))
                Glide.with(CouponActivity.this).load( "http://155.230.91.237:26002/MissionPlace/GDHGR.png").into(imageView);
            else if("강정보".equals(items.get(position)))
                Glide.with(CouponActivity.this).load( "http://155.230.91.237:26002/MissionPlace/GJB.png").into(imageView);
            else if("이월드".equals(items.get(position)))
                Glide.with(CouponActivity.this).load( "http://155.230.91.237:26002/MissionPlace/EWD.png").into(imageView);

            Log.d("check2","check2");

            return v;
        }
    }
}
