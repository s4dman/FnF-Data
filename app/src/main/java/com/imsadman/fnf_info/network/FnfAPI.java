package com.imsadman.fnf_info.network;

import com.imsadman.fnf_info.FnfModel;
import com.imsadman.fnf_info.database.FnfEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FnfAPI {

    @GET("friends/")
    Call<List<FnfModel>> getFriends(@Header("Authorization") String authToken);

    @POST("friends")
    Call<FnfModel> addFriend(@Body FnfEntity fnfEntity);

}
