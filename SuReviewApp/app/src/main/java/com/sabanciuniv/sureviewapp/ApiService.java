package com.sabanciuniv.sureviewapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("/api/auth/signin")
    Call<ResponseBody> signIn(@Body LoginRequest loginRequest);

    @GET("/api/users/profile")
    Call<User> getUserProfile(@Header("Authorization") String token);

    @PUT("/api/users/profile")
    Call<User> updateUserProfile(@Body User user, @Header("Authorization") String token);

    @POST("/api/users/create")
    Call<User> createUser(@Body User user);
}