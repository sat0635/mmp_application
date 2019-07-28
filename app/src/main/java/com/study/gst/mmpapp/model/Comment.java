package com.study.gst.mmpapp.model;

public class Comment {
    private String TITLE;
    private String CONTENT;
    private String IMAGE;

    public Comment(String title, String content, String image) {

        this.TITLE= title;
        this.CONTENT = content;
        this.IMAGE= image;

    }
    public String getTITLE() { return TITLE; }
    public String getCONTENT(){ return CONTENT;  }
    public String getIMAGE(){ return IMAGE; }
}
