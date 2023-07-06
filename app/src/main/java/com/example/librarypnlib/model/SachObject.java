package com.example.librarypnlib.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sach")
public class SachObject {
    @PrimaryKey (autoGenerate = true)
    private int maSach;
    private String tenSach;
    private int giaThue;
    private int maLoai;

    public SachObject() {
    }

    public SachObject(String tenSach, int giaThue, int maLoai) {
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    @Override
    public String toString() {
        return maSach+"."+tenSach+"."+giaThue+"."+maLoai;
    }
}
