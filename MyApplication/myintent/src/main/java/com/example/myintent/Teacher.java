package com.example.myintent;

import android.os.Parcel;
import android.os.Parcelable;

public class Teacher implements Parcelable {
    public String name;
    public int age;

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 页面B后读取
    protected Teacher(Parcel in) {
        // 从Parcel对象里面读取成员赋给name，age
        name = in.readString();
        age = in.readInt();
    }

    // 页面A先写入
    // 把属性写入到Parcel对象里
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 写的顺序和下面读的顺序必须一致
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {

        // 创建Teacher对象，并且构建好Parcel对象，传递给Teacher
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };
}
