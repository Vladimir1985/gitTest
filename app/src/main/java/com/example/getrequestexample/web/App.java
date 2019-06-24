package com.example.getrequestexample.web;

import com.example.getrequestexample.data.DataSources;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App {
    private static Retrofit retrofit;


    public  static ShopsApi getShopsApi(){
        ShopsApi shopsApi;
        retrofit = new Retrofit.Builder()
                .baseUrl(DataSources.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        shopsApi = retrofit.create(ShopsApi.class);
        return shopsApi;
    }

    public static DiscountsApi getDiscountsApi(){
        DiscountsApi discountsApi;
        retrofit = new Retrofit.Builder()
                .baseUrl(DataSources.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        discountsApi = retrofit.create(DiscountsApi.class);
        return discountsApi;
    }

    public static DiscountsImagesApi getDiscountsImagesApi(){
        DiscountsImagesApi discountsImagesApi;
        retrofit = new Retrofit.Builder()
                .baseUrl(DataSources.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        discountsImagesApi= retrofit.create(DiscountsImagesApi.class);
        return discountsImagesApi;
    }

}
