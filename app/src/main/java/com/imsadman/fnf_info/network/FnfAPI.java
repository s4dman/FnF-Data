package com.imsadman.fnf_info.network;

import com.imsadman.fnf_info.FnfModel;
import com.imsadman.fnf_info.database.FnfEntity;

import java.lang.reflect.Array;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FnfAPI {

    @FormUrlEncoded
    @POST("api/token/")
    Call<ResponseBody> auth(@Field("username") String username, @Field("password") String password);

    @GET("friends/")
    Call<List<FnfModel>> getFriends(@Header("Authorization") String authToken);

    @POST("friends")
    Call<FnfModel> addFriend(@Body FnfEntity fnfEntity);

}
