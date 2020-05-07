package com.imsadman.fnf_info.database;

import android.app.Activity;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.imsadman.fnf_info.network.Generics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FnfRepository {

    private static final String TAG = Activity.class.getSimpleName();

    private FnfDao mFnfDao;
    private LiveData<List<FnfEntity>> mFnfList;

    public FnfRepository(Application application) {
        FnfDatabase fnfDatabase = FnfDatabase.getFnfDatabaseInstance(application);
        mFnfDao = fnfDatabase.fnfDao();
        mFnfList = mFnfDao.getAllOrderByName();
    }

    public LiveData<List<FnfEntity>> getFnfList() {
        return mFnfList;
    }

    public void authRequest() {
        Call<ResponseBody> authCall = Generics.getFnfAPI().auth("guest", "hidjango");
        authCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {
                            JSONObject authObject = new JSONObject(response.body().string());
                            String authToken = authObject.getString("access");
                            fnfRequest(authToken);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "Authentication Error: " + t.getMessage());
            }
        });
    }

    public void fnfRequest(String authToken) {

        Call<List<FnfEntity>> fnfCall = Generics.getFnfAPI().getFriends("Bearer " + authToken);
        fnfCall.enqueue(new Callback<List<FnfEntity>>() {
            @Override
            public void onResponse(Call<List<FnfEntity>> call, Response<List<FnfEntity>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<FnfEntity> fnfEntityList = response.body();
                        for (FnfEntity fnfEntity : fnfEntityList) {
                            insert(fnfEntity); /*Inserting into local DB*/
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FnfEntity>> call, Throwable t) {
                Log.d(TAG, "fnfRequest Error: " + t.getMessage());
            }
        });
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
