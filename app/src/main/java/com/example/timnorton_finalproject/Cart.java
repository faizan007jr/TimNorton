package com.example.timnorton_finalproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private int productID;
    private int qty;

    public Cart(int productID, int qty) {
        this.productID = productID;
        this.qty = qty;
    }

    protected Cart(Parcel in) {
        productID = in.readInt();
        qty = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productID);
        dest.writeInt(qty);
    }
}
