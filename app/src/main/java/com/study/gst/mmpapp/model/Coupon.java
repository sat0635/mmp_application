package com.study.gst.mmpapp.model;

public class Coupon {

    private String STORENAME;
    private String NAME;
    private String IMAGE;
    private int COUPONID;

    public Coupon(String storename, String name, String image, int couponid) {

        this.STORENAME = storename;
        this.NAME = name;
        this.IMAGE = image;
        this.COUPONID = couponid;
    }
    public String getSTORENAME() { return STORENAME; }
    public String getNAME(){
        return NAME;
    }
    public String getIMAGE(){
        return IMAGE;
    }
    public int getCOUPONID() { return COUPONID;}
}
