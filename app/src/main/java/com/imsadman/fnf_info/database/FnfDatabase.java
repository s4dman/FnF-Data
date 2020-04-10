package com.imsadman.fnf_info.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = FnfEntity.class, version = 1)
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

//    public static synchronized FnfDatabase getInstance(Context context) {
//
//    }

//    private static RoomDatabase.Callback roomCallback = new Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            generateDb(fnfDatabaseInstance);
//        }
//
//    };
//
//
//    private static void generateDb(FnfDatabase fnfDatabaseInstance) {
//        FnfDao mFnfDao = null;
//
//        Call<List<FnfEntity>> getCall = HelperService.getFnfAPI().getFriends();
//
//        getCall.enqueue(new retrofit2.Callback<List<FnfEntity>>() {
//            @Override
//            public void onResponse(Call<List<FnfEntity>> call, Response<List<FnfEntity>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    mFnfDao.insert(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<FnfEntity>> call, Throwable t) {
//
//            }
//        });
//    }
}
