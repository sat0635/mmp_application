package com.study.gst.mmpapp.Intro;

import android.os.AsyncTask;

import com.study.gst.mmpapp.HTTP.HTTPCommunication;
import com.study.gst.mmpapp.model.Picture;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
public class GalleryData {

    ArrayList<Picture> items = new ArrayList<Picture>();

    public ArrayList<Picture> getItems() {

        new JSONTask().execute("http://172.30.1.9:8080/get/tourlist");


        Picture picture1 = new Picture("http://155.230.91.237:26002/Gallery/picture1.png",
                "대구 중구", "김광석거리", "2019/06/12");

        Picture picture2 = new Picture("http://155.230.91.237:26002/Gallery/picture2.png",
                "대구 서구", "팔공산", "2019/06/13");

        Picture picture3 = new Picture("http://155.230.91.237:26002/Gallery/picture3.png",
                "대구 북구", "강정보", "2019/06/21");

        items.add(picture1);
        items.add(picture2);
        items.add(picture3);

        items.add(picture1);
        items.add(picture2);
        items.add(picture3);

        items.add(picture1);
        items.add(picture2);
        items.add(picture3);

        return items;
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
            try {
                JSONArray GalleryListJson = new JSONArray(result);
                for(int i = 0; i < GalleryListJson.length(); i++) {
                    items.add(new Picture(GalleryListJson.getJSONObject(i)));
                }
            } catch (JSONException je) {
                je.printStackTrace();
            }



        }

    }
}