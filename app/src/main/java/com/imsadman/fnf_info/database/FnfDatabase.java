package com.imsadman.fnf_info.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.imsadman.fnf_info.network.HelperService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

@Database(entities = FnfEntity.class, version = 2, exportSchema = false)
public abstract class FnfDatabase extends RoomDatabase {

    public abstract FnfDao fnfDao();

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


    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

}
