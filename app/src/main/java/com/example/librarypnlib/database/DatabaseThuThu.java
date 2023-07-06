package com.example.librarypnlib.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.librarypnlib.dao.ThuThuDao;
import com.example.librarypnlib.model.ThuThuObject;

@Database(entities = {ThuThuObject.class},version = 1)
public abstract class DatabaseThuThu extends RoomDatabase {
    public abstract ThuThuDao thuThuDao();
    public static final String DATABASENAME="ThuThu.db";
    public static DatabaseThuThu Instance;
    public static synchronized DatabaseThuThu getInstance(Context context){
        if(Instance ==null){
            Instance = Room.databaseBuilder(context, DatabaseThuThu.class,DATABASENAME).
                    allowMainThreadQueries().build();
        }
        return Instance;
    }
}

