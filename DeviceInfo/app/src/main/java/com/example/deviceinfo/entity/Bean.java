package com.example.deviceinfo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Bean implements Parcelable {
    String key;
    String value;

    public Bean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    protected Bean(Parcel in) {
        key = in.readString();
        value = in.readString();
    }

    public static final Creator<Bean> CREATOR = new Creator<Bean>() {
        @Override
        public Bean createFromParcel(Parcel in) {
            return new Bean(in);
        }

        @Override
        public Bean[] newArray(int size) {
            return new Bean[size];
        }
    };

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeString(value);
    }
}
