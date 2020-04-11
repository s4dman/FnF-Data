package com.imsadman.fnf_info.database;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FnfRepository {

    private static final String TAG = Activity.class.getSimpleName();

    private FnfDao mFnfDao;
    private LiveData<List<FnfEntity>> mAllFnf;

    public FnfRepository(Application application) {
        FnfDatabase fnfDatabase = FnfDatabase.getFnfDatabaseInstance(application);
        mFnfDao = fnfDatabase.fnfDao();
        mAllFnf = mFnfDao.getAllOrderByName();
    }

    /* TODO: Check only getData > Then post/get data from API */

    public LiveData<List<FnfEntity>> getAllFnf() {
        return mAllFnf;
    }

    public void insert(FnfEntity fnfEntity) {
        new insertAsyncTask(mFnfDao).execute(fnfEntity);
    }

    private static class insertAsyncTask extends AsyncTask<FnfEntity, Void, Void> {

        private FnfDao mAsyncTaskDao;

        insertAsyncTask(FnfDao fnfDao) {
            mAsyncTaskDao = fnfDao;
        }

        @Override
        protected Void doInBackground(final FnfEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
