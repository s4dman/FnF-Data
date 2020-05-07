package com.imsadman.fnf_info.repository;

import com.imsadman.fnf_info.repository.model.FnfEntity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface FnfAPI {

    @FormUrlEncoded
    @POST("api/token/")
    Call<ResponseBody> auth(@Field("username") String username, @Field("password") String password);

    @GET("friends/")
    Call<List<FnfEntity>> getFriends(@Header("Authorization") String authToken);

    /*TODO*/
    @POST("friends")
    Call<FnfEntity> addFriend(@Body FnfEntity fnfEntity);

    /*TODO*/
    @PUT("friends")
    Call<FnfEntity> updateFriend(@Body FnfEntity fnfEntity);

    /*TODO*/
    @DELETE("friends")
    Call<FnfEntity> deleteFriend(@Body FnfEntity fnfEntity);

}
