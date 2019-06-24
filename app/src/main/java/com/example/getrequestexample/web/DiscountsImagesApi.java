package com.example.getrequestexample.web;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DiscountsImagesApi {
    @GET
    Call<ResponseBody> getData(@Url String fileUrler);
}
