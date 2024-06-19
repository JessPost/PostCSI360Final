package com.example.postcsi360final;

import android.os.Parcel;
import android.os.Parcelable;

public class CoffeeOrder implements Parcelable {
    String description;
    int quantity;
    double total;

    public CoffeeOrder(String description, int quantity, double total) {
        this.description = description;
        this.quantity = quantity;
        this.total = total;
    }

    protected CoffeeOrder(Parcel in) {
        description = in.readString();
        quantity = in.readInt();
        total = in.readDouble();
    }

    public static final Creator<CoffeeOrder> CREATOR = new Creator<CoffeeOrder>() {
        @Override
        public CoffeeOrder createFromParcel(Parcel in) {
            return new CoffeeOrder(in);
        }

        @Override
        public CoffeeOrder[] newArray(int size) {
            return new CoffeeOrder[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeInt(quantity);
        dest.writeDouble(total);
    }
}
