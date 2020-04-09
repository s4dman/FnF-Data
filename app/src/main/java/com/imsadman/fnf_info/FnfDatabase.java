package com.imsadman.fnf_info;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = FnfEntity.class, version = 1)
public abstract class FnfDatabase extends RoomDatabase {

    public abstract FnfDao fnfDao();

    private static FnfDatabase fnfDatabaseInstance;

    public static synchronized FnfDatabase getInstance(Context context) {
        if (fnfDatabaseInstance == null) {
            fnfDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    FnfDatabase.class,
                    "fnf_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return fnfDatabaseInstance;
    }
}
