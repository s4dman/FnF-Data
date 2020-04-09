package com.imsadman.fnf_info.Services;

import com.imsadman.fnf_info.FnfEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FnfAPI {

    @GET("friends")
    Call<List<FnfEntity>> getFriends();

    @POST("friends")
    Call<FnfEntity> addFriend(@Body FnfEntity fnfEntity);

}