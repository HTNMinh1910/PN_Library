package com.example.librarypnlib.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.librarypnlib.model.ThuThuObject;

import java.util.List;

@Dao
public interface ThuThuDao {
    @Insert
    void insert(ThuThuObject thuThuObject);

    @Query("SELECT * FROM ThuThu")
    List<ThuThuObject> getAllData();

    @Delete
    void delete(ThuThuObject thuThuObject);

    @Update
    void edit(ThuThuObject thuThuObject);

    @Query("UPDATE ThuThu SET matKhau = :pass WHERE maTT like :ID")
    void getById(String ID, String pass);

    @Query("SELECT * FROM ThuThu WHERE maTT = :user AND matKhau = :password")
    int checkLogin(String user, String password);
}
