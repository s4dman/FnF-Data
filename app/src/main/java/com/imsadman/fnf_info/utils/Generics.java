package com.imsadman.fnf_info.utils;

import android.app.Activity;

import com.imsadman.fnf_info.repository.FnfAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Generics {

    private static final String TAG = Activity.class.getSimpleName();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://s4dman.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static FnfAPI mFnfAPI = retrofit.create(FnfAPI.class);

    public static FnfAPI getFnfAPI() {
        return mFnfAPI;
    }

}
