package com.study.gst.mmpapp.model;

//인증샷 갤러리의 인증샷
public class Picture {
    private int ID;
    private String TITLE;
    private String CONTENT;
    private String IMAGE;
    private int LIKE;


    public Picture(int id, String title, String content, String image) {
        this.ID = id;
        this.TITLE = title;
        this.CONTENT = content;
        this.IMAGE = image;
    }

    public int getID() { return ID; }

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