package com.example.librarypnlib.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LoaiSach")
public class LoaiSachObject {
    @PrimaryKey (autoGenerate = true)
    private int maLoai;
    private String tenLoai;

    public LoaiSachObject() {
    }

    public LoaiSachObject(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public String toString() {
        return maLoai+"."+tenLoai;
    }
}
