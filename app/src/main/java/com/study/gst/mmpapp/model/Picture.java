package com.study.gst.mmpapp.model;

//인증샷 갤러리의 인증샷
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