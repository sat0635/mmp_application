package com.study.gst.mmpapp.model;


import org.json.JSONObject;
import org.json.JSONException;
//메인페이지 아이템 객체
public class Picture {
    private String TITLE;
    private String CONTENT;
    private String IMAGE;
    private int like;


    public Picture(String title, String content, String image) {

        this.TITLE = title;
        this.CONTENT = content;
        this.IMAGE = image;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public String getIMAGE() {
        return IMAGE;
    }
}