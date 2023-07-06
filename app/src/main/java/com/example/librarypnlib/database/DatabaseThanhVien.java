package com.example.librarypnlib.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.librarypnlib.dao.ThanhVienDao;
import com.example.librarypnlib.model.ThanhVienObject;

@Database(entities = {ThanhVienObject.class},version = 1)
public abstract class DatabaseThanhVien extends RoomDatabase {
    public abstract ThanhVienDao thanhVienDao();
    public static final String DATABASENAME="ThanhVien.db";
    public static DatabaseThanhVien Instance;
    public static synchronized DatabaseThanhVien getInstance(Context context){
        if(Instance == null){
            Instance = Room.databaseBuilder(context,DatabaseThanhVien.class,DATABASENAME).
                    allowMainThreadQueries().build();
        }
        return Instance;
    }
}
