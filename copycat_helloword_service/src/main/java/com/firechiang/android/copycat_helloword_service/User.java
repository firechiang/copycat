package com.firechiang.android.copycat_helloword_service;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * 这个对象用于传递给其它App的返回值（注意：这个类一定要实现 Parcelable 接口，否则无法传递给其它App）
 */
public class User implements Parcelable {

    private String name;

    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected User(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    /**
     * 这个是解包（注意：这个可自动生成）
     */
    public static final Creator<User> CREATOR = new Creator<User>() {
        /**
         * 解包
         * @param in
         * @return
         */
        @Override
        public User createFromParcel(Parcel in) {
            Log.i("TAG","解包："+User.class.getName()+"完成");
            return new User(in);
        }

        /**
         * 返回一个指定大小的数组
         * @param size
         * @return
         */
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将所有属性写到 Parcel 对象，其实就是打包（注意：这个函数可自动生成）
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        Log.i("TAG","打包："+User.class.getName()+"完成");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
