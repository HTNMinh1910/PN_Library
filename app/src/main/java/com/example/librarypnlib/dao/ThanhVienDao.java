package com.example.librarypnlib.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.librarypnlib.model.ThanhVienObject;

import java.util.List;
@Dao
public interface ThanhVienDao {
        @Insert
        void insert(ThanhVienObject thanhVienObject);
        @Query("SELECT * FROM ThanhVien")
        List<ThanhVienObject> getAllData();
        @Delete
        void delete(ThanhVienObject thanhVienObject);
        @Update
        void edit(ThanhVienObject thanhVienObject);
        @Query("SELECT * FROM ThanhVien WHERE maTV = :ID")
        ThanhVienObject getById(int ID);
}
