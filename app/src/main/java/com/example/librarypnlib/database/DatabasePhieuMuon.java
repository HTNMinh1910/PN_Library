package com.example.librarypnlib.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.librarypnlib.dao.PhieuMuonDao;
import com.example.librarypnlib.model.PhieuMuonObject;


@Database(entities = {PhieuMuonObject.class},version = 1)
public abstract class DatabasePhieuMuon extends RoomDatabase {
    public abstract PhieuMuonDao phieuMuonDao();
    public static final String DATABASENAME="PhieuMuon.db";
    public static DatabasePhieuMuon Instance;
    public static synchronized DatabasePhieuMuon getInstance(Context context){
        if(Instance ==null){
            Instance = Room.databaseBuilder(context, DatabasePhieuMuon.class,DATABASENAME).
                    allowMainThreadQueries().build();
        }
        return Instance;
    }
}

