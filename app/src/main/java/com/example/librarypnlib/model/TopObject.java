package com.example.librarypnlib.model;

import androidx.room.Entity;

@Entity(tableName = "Top")
public class TopObject {
    int maSach;
    String SoLuong;

    public TopObject() {
    }

    public TopObject(int maSach, String soLuong) {
        this.maSach = maSach;
        SoLuong = soLuong;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }
}
