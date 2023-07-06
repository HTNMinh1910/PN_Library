package com.example.librarypnlib.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.librarypnlib.model.SachObject;

import java.util.List;

@Dao
public interface SachDao {
        @Insert
        void insert(SachObject sachObject);

        @Query("SELECT * FROM Sach")
        List<SachObject> getAllData();

        @Delete
        void delete(SachObject sachObject);
        @Update
        void edit(SachObject sachObject);

        @Query("SELECT * FROM Sach WHERE maSach = :ID")
        SachObject getById(int ID);
}
