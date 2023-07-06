package com.example.librarypnlib.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ThuThu")
public class ThuThuObject {
    @PrimaryKey (autoGenerate = true)
    private int idTT;
    private String maTT;
    private String hoTen;
    private String matKhau;

    public ThuThuObject() {
    }

    public ThuThuObject(String maTT, String hoTen, String matKhau) {
        this.maTT = maTT;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public int getIdTT() {
        return idTT;
    }

    public void setIdTT(int idTT) {
        this.idTT = idTT;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
