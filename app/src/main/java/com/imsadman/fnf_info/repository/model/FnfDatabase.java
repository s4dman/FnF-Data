package com.imsadman.fnf_info.repository.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = FnfEntity.class, version = 3, exportSchema = false)
public abstract class FnfDatabase extends RoomDatabase {

    private static FnfDatabase fnfDatabaseInstance;

    static FnfDatabase getFnfDatabaseInstance(final Context context) {
        if (fnfDatabaseInstance == null) {
            synchronized (FnfDatabase.class) {
                if (fnfDatabaseInstance == null) {
                    fnfDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            FnfDatabase.class,
                            "fnf_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return fnfDatabaseInstance;
    }

    public abstract FnfDao fnfDao();
}
