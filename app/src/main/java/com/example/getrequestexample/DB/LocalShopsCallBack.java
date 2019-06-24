package com.example.getrequestexample.DB;

import android.support.annotation.NonNull;

import com.example.getrequestexample.data.Shop;

import java.util.List;


public interface LocalShopsCallBack {


        interface GetLocalShopsCallback {

            void onShopsLoaded(List<Shop> shops);

            void onDataNotAvailable();
        }



        void getLocalShops(@NonNull GetLocalShopsCallback callback);
        void saveShopsJson (@NonNull List<Shop> shops);

}
