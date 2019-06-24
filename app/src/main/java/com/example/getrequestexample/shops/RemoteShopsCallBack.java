package com.example.getrequestexample.shops;

import android.support.annotation.NonNull;

import com.example.getrequestexample.data.Shop;

import java.util.List;

//Колбэк для удаленного доступа
public interface RemoteShopsCallBack {

    interface GetRemoteShopsCallback {

        void onShopsLoaded(List<Shop> shops);

        void onDataNotAvailable();
    }

    void getRemoteShops(@NonNull GetRemoteShopsCallback callback);
}
