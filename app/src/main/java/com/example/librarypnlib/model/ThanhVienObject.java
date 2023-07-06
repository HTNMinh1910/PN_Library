package com.example.librarypnlib.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ThanhVien")
public class ThanhVienObject {
    @PrimaryKey(autoGenerate = true)
    private int maTV;
    private String hoTen;
    private String namSinh;

    public ThanhVienObject() {
    }

    public ThanhVienObject(String hoTen, String namSinh) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return hoTen;
    }
}
