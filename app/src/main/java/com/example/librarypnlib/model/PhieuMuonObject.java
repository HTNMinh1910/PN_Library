package com.example.librarypnlib.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "PhieuMuon")
public class PhieuMuonObject {
    @PrimaryKey (autoGenerate = true)
    private int maPM;
    private String maTT;
    private int maTV;
    private int maSach;
    private int tienThue;
    private int traSach;
    private String ngay;

    public PhieuMuonObject() {
    }

    public PhieuMuonObject(String maTT, int maTV, int maSach, int tienThue, int traSach, String ngay) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.ngay = ngay;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "Mã PM: " + maPM +
                "\nTên TT: " + maTT +
                "\nTiền Thuê: " + tienThue +
                "\nTrả Sách: " + traSach +
                "\nNgày: " + ngay;
    }
}
