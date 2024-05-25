package com.sabanciuniv.sureviewapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("auth/signin")
    Call<ResponseBody> signIn(@Body LoginRequest loginRequest);
}