package com.study.gst.mmpapp.NearInfo;

import java.util.ArrayList;

public class NearStoreGalleryData {

    ArrayList<NearStoreImage> items = new ArrayList<>();

    public ArrayList<NearStoreImage> getItems() {


        NearStoreImage place1 = new NearStoreImage("http://155.230.91.237:26002/Gallery/picture1.png",
                "대구 중구","김광석 거리", "2km");

        NearStoreImage place2 = new NearStoreImage("http://155.230.91.237:26002/Gallery/picture2.png",
                "대구 서구", "팔공산", "3km");

        NearStoreImage place3 = new NearStoreImage("http://155.230.91.237:26002/Gallery/picture3.png",
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
