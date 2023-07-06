package com.example.librarypnlib.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.librarypnlib.model.PhieuMuonObject;
import com.example.librarypnlib.model.TopObject;

import java.util.List;

@Dao
public interface PhieuMuonDao {
    @Insert
    void insert(PhieuMuonObject phieuMuonObject);

    @Query("SELECT * FROM PhieuMuon")
    List<PhieuMuonObject> getAllData();

    @Delete
    void delete(PhieuMuonObject phieuMuonObject);

    @Update
    void edit(PhieuMuonObject phieuMuonObject);

    @Query("SELECT * FROM PhieuMuon WHERE maPM = :ID")
    PhieuMuonObject getById(int ID);

    @Query("UPDATE PhieuMuon SET traSach = :trangthai WHERE maPM = :ID")
    void updateTrangThai(int ID, int trangthai);

    @Query("SELECT maSach, COUNT(maSach) as SoLuong FROM PhieuMuon GROUP BY maSach ORDER BY SoLuong DESC LIMIT 10")
    List<TopObject> getTopData();

    @Query("SELECT SUM(tienThue) AS DoanhThu FROM phieumuon WHERE ngay BETWEEN  :startDate AND :endDate")
    int getDoanhThu(String startDate,String endDate);
}
