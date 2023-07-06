package com.example.librarypnlib.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.librarypnlib.model.LoaiSachObject;

import java.util.List;

@Dao
public interface LoaiSachDao {
        @Insert
        void insert(LoaiSachObject loaiSachObject);
        @Query("SELECT * FROM LoaiSach")
        List<LoaiSachObject> getAllData();
        @Delete
        void delete(LoaiSachObject loaiSachObject);
        @Update
        void edit(LoaiSachObject loaiSachObject);

        @Query("SELECT * FROM LoaiSach WHERE maLoai = :ID")
        LoaiSachObject getById(int ID);
}
