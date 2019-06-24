package com.example.getrequestexample.web;

import com.example.getrequestexample.data.Shop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ShopsApi {
    @Headers("version:4")
    @GET("shops/")
    Call<List<Shop>> getData(@Header("If-Modified-Since") String if_modified);
}


