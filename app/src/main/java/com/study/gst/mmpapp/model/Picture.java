package com.study.gst.mmpapp.model;


import org.json.JSONObject;
import org.json.JSONException;
//메인페이지 아이템 객체
public class Picture {

    private String url;
    private String place;
    private String title;
    private String content;

    public Picture(String url, String place, String title, String content) {

        this.url = url;
        this.place = place;
        this.title = title;
        this.content = content;

    }

    public Picture(JSONObject o) {
    }

    public String getUrl() {
        return url;
    }

    public String getPlace() {
        return place;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
