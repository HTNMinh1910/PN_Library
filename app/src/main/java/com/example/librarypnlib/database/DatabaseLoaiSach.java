package com.example.librarypnlib.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.librarypnlib.dao.LoaiSachDao;
import com.example.librarypnlib.model.LoaiSachObject;

@Database(entities = {LoaiSachObject.class},version = 1)
public abstract class DatabaseLoaiSach extends RoomDatabase {
    public abstract LoaiSachDao LoaiSachDao();
    public static final String DATABASENAME="LoaiSach.db";
    public static DatabaseLoaiSach Instance;
    public static synchronized DatabaseLoaiSach getInstance(Context context){
        if(Instance ==null){
            Instance = Room.databaseBuilder(context,DatabaseLoaiSach.class,DATABASENAME).
                    allowMainThreadQueries().build();
        }
        return Instance;
    }
}

