package com.example.getrequestexample.web;

import com.example.getrequestexample.data.Discount;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface DiscountsApi {
    @Headers("version: 4")
    @GET("discounts/with-publisher/")
    Call<List<Discount>> getData(@Query("shopId") Integer id, @Query("publisher") String publisher);
}
