package com.imsadman.fnf_info.network;

import android.app.Activity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HelperService {

    private static final String TAG = Activity.class.getSimpleName();

    /*TODO: FUCK-IT Change API and Entites*/
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://s4dman.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static FnfAPI mFnfAPI = retrofit.create(FnfAPI.class);

    public static FnfAPI getFnfAPI() {
        return mFnfAPI;
    }

}
