package com.imsadman.fnf_info.network;

import android.app.Activity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HelperService {

    private static final String TAG = Activity.class.getSimpleName();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static FnfAPI mFnfAPI = retrofit.create(FnfAPI.class);

    public static FnfAPI getFnfAPI() {
        return mFnfAPI;
    }

}
