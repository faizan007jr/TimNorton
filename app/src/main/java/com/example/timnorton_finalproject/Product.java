package com.example.timnorton_finalproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Product implements Parcelable {
    private int _id;
    private String name;
    private String desc;
    private int imgResource;
    private double price;
    private ArrayList<String> details;

    public Product(int _id, String name, String desc, int imgResource, double price, ArrayList<String> details) {
        this._id = _id;
        this.name = name;
        this.desc = desc;
        this.imgResource = imgResource;
        this.price = price;
        this.details = details;
    }

    protected Product(Parcel in) {
        _id = in.readInt();
        name = in.readString();
        desc = in.readString();
        imgResource = in.readInt();
        price = in.readDouble();
        details = in.readArrayList(String.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getImgResource() {
        return imgResource;
    }

    public double getPrice() {
        return price;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeInt(imgResource);
        dest.writeDouble(price);
        dest.writeList(details);
    }
}