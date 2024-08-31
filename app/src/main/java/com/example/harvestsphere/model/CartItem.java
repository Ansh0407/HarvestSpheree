package com.example.harvestsphere.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {
    private String productName;
    private String productImage;
    private int quantity;

    public CartItem(String productName, String productImage, int quantity) {
        this.productName = productName;
        this.productImage = productImage;
        this.quantity = quantity;
    }

    protected CartItem(Parcel in) {
        productName = in.readString();
        productImage = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productName);
        parcel.writeString(productImage);
        parcel.writeInt(quantity);
    }
}