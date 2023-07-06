package com.example.librarypnlib.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.librarypnlib.dao.SachDao;
import com.example.librarypnlib.model.SachObject;

@Database(entities = {SachObject.class},version = 1)
public abstract class DatabaseSach extends RoomDatabase {
    public abstract SachDao sachDao();
    public static final String DATABASENAME="Sach.db";
    public static DatabaseSach Instance;
    public static synchronized DatabaseSach getInstance(Context context){
        if(Instance ==null){
            Instance = Room.databaseBuilder(context, DatabaseSach.class,DATABASENAME).
                    allowMainThreadQueries().build();
        }
        return Instance;
    }
}

