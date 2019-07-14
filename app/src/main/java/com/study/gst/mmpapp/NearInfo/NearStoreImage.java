package com.study.gst.mmpapp.NearInfo;

public class NearStoreImage {
    private String url;
    private String big_place;
    private String place;
    private String km;

    public NearStoreImage(String url,  String big_place, String place,String km) {

        this.url = url;
        this.big_place = big_place;
        this.place = place;
        this.km = km;

    }

    public String getUrl() {
        return url;
    }

    public String getPlace() {
        return place;
    }

    public String getKm() {
        return km;
    }

    public String getBig_place(){ return big_place; }
}
