package com.study.gst.mmpapp.NearInfo;

import java.util.ArrayList;

public class NearPlaceGalleryData {

    ArrayList<NearPlaceImage> items = new ArrayList<>();

    public ArrayList<NearPlaceImage> getItems() {

        NearPlaceImage place1 = new NearPlaceImage("http://155.230.91.237:26002/Gallery/picture1.png",
                "대구 중구","김광석 거리", "2km");

        NearPlaceImage place2 = new NearPlaceImage("http://155.230.91.237:26002/Gallery/picture2.png",
                "대구 서구", "팔공산", "3km");

        NearPlaceImage place3 = new NearPlaceImage("http://155.230.91.237:26002/Gallery/picture3.png",
                "대구 북구", "근대화골목", "4km");

        items.add(place1);
        items.add(place2);
        items.add(place3);

        items.add(place1);
        items.add(place2);
        items.add(place3);
        items.add(place1);
        items.add(place2);
        items.add(place3);
        items.add(place1);
        items.add(place2);
        items.add(place3);

        return items;
    }
}
