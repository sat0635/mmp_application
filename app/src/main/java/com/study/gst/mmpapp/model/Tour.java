package com.study.gst.mmpapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Tour implements Parcelable {
    private int id;
    private String name;
    private String context;
    private String phoneNumber;
    private String startTime;
    private String endTime;
    private int price;
    private String address;
    private String imageURL;

    public Tour(){}
    public Tour(String name, String context, String phoneNumber, String startTime, String endTime, int price, String address, String imageURL) {
        this.name = name;
        this.context = context;
        this.phoneNumber = phoneNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.address = address;
        this.imageURL = imageURL;
    }
    public Tour(JSONObject o) {
        try {
            this.id = o.getInt("id");
            this.name = o.getString("name");
            this.context = o.getString("context");
            this.phoneNumber = o.getString("phoneNumber");
            this.startTime = o.getString("startTime");
            this.endTime = o.getString("endTime");
            this.price = o.getInt("price");
            this.address = o.getString("address");
            this.imageURL = o.getString("imageURL");
        } catch (JSONException je) {
            je.printStackTrace();
            new Tour();
        }
    }


    protected Tour(Parcel in) {
        id = in.readInt();
        name = in.readString();
        context = in.readString();
        phoneNumber = in.readString();
        price = in.readInt();
        address = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        imageURL = in.readString();
    }

    public String getTourInfo() {
        return context + "\n\n" + "입장료: " + price + "\n" + "시간:" + startTime + "~" + endTime;
    }



    public static final Creator<Tour> CREATOR = new Creator<Tour>() {
        @Override
        public Tour createFromParcel(Parcel in) {
            return new Tour(in);
        }

        @Override
        public Tour[] newArray(int size) {
            return new Tour[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) { this.context = context; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(context);
        parcel.writeString(phoneNumber);
        parcel.writeInt(price);
        parcel.writeString(address);
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeString(imageURL);
    }
}
